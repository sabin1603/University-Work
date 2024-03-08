#include "SongCollection.h"
#include <iostream>
#include <stdexcept>

int main() {
    try {
        SongCollection collection("songs.txt");
        const auto& songs = collection.getSongs();
        for (const auto& song : songs) {
            std::cout << song.getArtist() << " - " << song.getTitle() << "\n";
        }
    }
    catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << "\n";
    }
    Song song1("Artist1", "Title1", "Lyrics for song 1");
    Song song2("Artist1", "Title2", "Lyrics for song 2");
    Song song3("Artist2", "Title3", "Lyrics for song 3");
    Song song4("Artist2", "Title4", "Lyrics for song 4");
    Song song5("Artist3", "Title5", "Lyrics for song 5");
    Song song6("Artist3", "Title6", "Lyrics for song 6");
    Song song7("Artist4", "Title7", "Lyrics for song 7");
    Song song8("Artist4", "Title8", "Lyrics for song 8");
    Song song9("Artist5", "Title9", "Lyrics for song 9");
    Song song10("Artist5", "Title10", "Lyrics for song 10");
    Song song11("Artist6", "Title11", "Lyrics for song 11");
    Song song12("Artist6", "Title12", "Lyrics for song 12");
    Song song13("Artist7", "Title13", "Lyrics for song 13");
    Song song14("Artist7", "Title14", "Lyrics for song 14");
    Song song15("Artist1", "Title15", "Lyrics for song 15");
    Song song16("Artist2", "Title16", "Lyrics for song 16");
    Song song17("Artist3", "Title17", "Lyrics for song 17");
    Song song18("Artist4", "Title18", "Lyrics for song 18");
    Song song19("Artist5", "Title19", "Lyrics for song 19");
    Song song20("Artist6", "Title20", "Lyrics for song 20");

    std::vector<Song> songs;
    songs.push_back(song1);
    songs.push_back(song2);
    songs.push_back(song3);
    songs.push_back(song4);
    songs.push_back(song5);
    songs.push_back(song6);
    songs.push_back(song7);
    songs.push_back(song8);
    songs.push_back(song9);
    songs.push_back(song10);
    songs.push_back(song11);
    songs.push_back(song12);
    songs.push_back(song13);
    songs.push_back(song14);
    songs.push_back(song15);
    songs.push_back(song16);
    songs.push_back(song17);
    songs.push_back(song18);
    songs.push_back(song19);
    songs.push_back(song20);

    return 0;
}