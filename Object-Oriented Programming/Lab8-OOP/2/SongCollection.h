#pragma once
#include <vector>
#include <string>
#include "Song.h"
#include <set>
#include <map>

class SongCollection {
private:
    std::vector<Song> songs;

public:
    SongCollection(const std::string& filename);
    SongCollection();

    void loadSongsFromFile(std::string filename);
    std::set<std::string> getUniqueArtists() const;
    std::vector<Song> getSongsByArtist(std::string artist) const;
    std::vector<const Song*> searchSongs(const std::string& word) const;
    const std::vector<Song>& getSongs() const;
    void addSong(Song song);
   
    
  
};
