#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);

    QWidget w;
    QHBoxLayout* ly = new QHBoxLayout{};
    QListWidget* listWidget = new QListWidget{};
    QListWidget* listWidget1 = new QListWidget{};
    w.setLayout(ly);

    // Left column: All song list
    QVBoxLayout* leftLayout = new QVBoxLayout{};
    leftLayout->addWidget(new QLabel{ "All Songs:" });
    leftLayout->addWidget(listWidget);
    ly->addLayout(leftLayout);

    leftLayout->addWidget(new QLabel{ "Title:" });
    leftLayout->addWidget(new QLineEdit);
    leftLayout->addWidget(new QLabel{ "Artist:" });
    leftLayout->addWidget(new QLineEdit);
    leftLayout->addWidget(new QLabel{ "Duration:" });
    leftLayout->addWidget(new QLineEdit);
    leftLayout->addWidget(new QLabel{ "Link:" });
    leftLayout->addWidget(new QLineEdit);
    QHBoxLayout* buttonsLayout = new QHBoxLayout{};
    buttonsLayout->addWidget(new QPushButton{ "Add" });
    buttonsLayout->addWidget(new QPushButton{ "Remove" });
    buttonsLayout->addWidget(new QPushButton{ "Update" });




    leftLayout->addLayout(buttonsLayout);
    leftLayout->addWidget(new QPushButton{ "Filter" });


    // Middle column: > button
    QVBoxLayout* middleLayout = new QVBoxLayout{};
    middleLayout->addWidget(new QPushButton{ ">" });
    ly->addLayout(middleLayout);

    // Right column: Playlist list
    QVBoxLayout* rightLayout = new QVBoxLayout{};
    rightLayout->addWidget(new QLabel{ "Playlist:" });
    rightLayout->addWidget(listWidget1);
    ly->addLayout(rightLayout);

    QHBoxLayout* buttonsLayout1 = new QHBoxLayout{};
    buttonsLayout1->addWidget(new QPushButton{ "Play" });
    buttonsLayout1->addWidget(new QPushButton{ "Next" });
    rightLayout->addLayout(buttonsLayout1);





    w.show();
    return a.exec();
}