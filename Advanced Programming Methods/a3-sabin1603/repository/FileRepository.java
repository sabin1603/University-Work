package repository;

import domain.Identifiable;
//import CarRentals.Utils.ParsingHelper;

import java.io.IOException;

public abstract class FileRepository<ID, T extends Identifiable<ID>> extends GenericRepository<ID, T>{
    protected String filename;
    public FileRepository(String filename) throws IOException{
        this.filename = filename;
        readFromFile();
    }

    protected abstract void readFromFile() throws IOException;
    public abstract void writeToFile();
    @Override
    public T add(T elem) {
        T result = super.add(elem);
        writeToFile();

        return result;
    }
    @Override
    public T remove(ID id){
        T result = super.remove(id);
        writeToFile();

        return result;
    }
    @Override
    public T update(ID id, T elem){
        T result = super.update(id, elem);
        writeToFile();

        return result;
    }
}