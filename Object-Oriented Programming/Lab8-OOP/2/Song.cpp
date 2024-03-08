#include "Song.h"
#include <cctype>
#include <iostream>

Song::Song(std::string artist, std::string title, std::string lyrics) : artist(artist), title(title) {
    std::string word;
    for (char c : lyrics) {
        if (std::isalnum(c)) {
            word.push_back(std::tolower(c));
        }
        else if (!word.empty()) {
            this->lyrics.push_back(word);
            word.clear();
        }
    }
    if (!word.empty()) {
        this->lyrics.push_back(word);
    }
}

std::string Song::getArtist() const {
    return artist;
}

std::string Song::getTitle() const {
    return title;
}

std::vector<std::string> Song::getLyrics() const {
    return lyrics;
}
