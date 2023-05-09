#include <iostream>
#include <fstream>
#include <string>

using namespace std;
void lastKLines(string filename, int lastLines);

//12.1 Last K Lines
void lastKLinesHelper(int lastLines)
{
    ofstream MyFile("myfile.txt");
    for (int i = 1; i <= 10; i++) {
        MyFile << "Line ";
        MyFile << i << endl;
    }
    lastKLines("myfile.txt", lastLines);
    MyFile.close();
}

void lastKLines (string filename, int lastLines)
{
    string line;
    ifstream MyReadFile(filename);
    int linesCount = 0;
    while(getline(MyReadFile, line)){
        linesCount++;
    }
    MyReadFile.clear();
    MyReadFile.seekg(0);
    int i;
    for(i = 0; i < (linesCount - lastLines); i++)
    {
        getline(MyReadFile, line);
    }
    while(i < linesCount)
    {
        getline(MyReadFile, line);
        cout << line << endl;
        i++;
    }
    MyReadFile.close();
}
//12.2 Reverse String
void reverse (char* str)
{
    int stringSize = 0;
    string reversed = "";
    while(*str != '\0'){
        cout << *str;
        stringSize++;
        str++;
    }
    cout << endl;
    while(stringSize > 0)
    {
        reversed += *(--str);
        stringSize--;
    }
    cout << reversed;
}
int main()
{
    //lastKLinesHelper(3);
    reverse("somestring\0");
    return 0;
}
