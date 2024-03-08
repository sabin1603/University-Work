#include "SongCollection.h"
#include <fstream>
#include <stdexcept>
#include <sstream>
#include <set>
#include <algorithm>
#include <unordered_map>



SongCollection::SongCollection(const std::string& filename) {
    std::ifstream file(filename);
    if (!file) {
        throw std::invalid_argument("Failed to open file.");
    }

    std::string artist, title, lyrics;
    while (std::getline(file, artist) && std::getline(file, title) && std::getline(file, lyrics)) {
        Song song(artist, title, lyrics);
        songs.emplace_back(song);
    }
}


const std::vector<Song>& SongCollection::getSongs() const {
    return songs;
}

void SongCollection::addSong(Song song) {
    songs.push_back(song);
}


std::set<std::string> SongCollection::getUniqueArtists() const {
    std::set<std::string> uniqueArtists;
    for (const auto& song : songs) {
        uniqueArtists.insert(song.getArtist());
    }
    return uniqueArtists;
}

std::vector<Song> SongCollection::getSongsByArtist(std::string artist) const {
    std::vector<Song> songsByArtist;
    for (const auto& song : songs) {
        if (song.getArtist() == artist) {
            songsByArtist.emplace_back(song);
        }
    }
    return songsByArtist;
}

std::vector<const Song*> SongCollection::searchSongs(const std::string& word) const {
    std::vector<const Song*> result;

    for (const auto& song : songs) {
        const auto& lyrics = song.getLyrics();
        if (std::any_of(lyrics.begin(), lyrics.end(), [&](const std::string& line) {
            return line.find(word) != std::string::npos;
            })) {
            result.push_back(&song);
        }
    }

    return result;
}









