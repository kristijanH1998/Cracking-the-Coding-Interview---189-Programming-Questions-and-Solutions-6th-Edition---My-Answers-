#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>

using namespace std;
void lastKLines(string filename, int lastLines);
class Node {
    int value = -1;
    Node * firstNode = NULL;
    Node * secondNode = NULL;

    public: Node(int v){
        value = v;
    }
    int getVal(){
        if(value != -1){
            return value;
        }
    }
    void setVal(int v){
        value = v;
    }
    Node* setFirst(Node * first){
        firstNode = first;
        return this;
    }
    Node* setSecond(Node * second){
        secondNode = second;
        return this;
    }
    Node* getFirst(){
        if(this -> firstNode){
            return this -> firstNode;
        } else {
            return NULL;
        }
    }
    Node* getSecond(){
        if(this -> secondNode){
            return this -> secondNode;
        } else {
            return NULL;
        }
    }
};
Node* copyNode (Node* node, unordered_map<Node*, int> * hashmap);
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
//12.8 Copy Node
Node* copyNodeHelper(){
    Node *n1 = new Node(1);
    Node *n2 = new Node(2);
    Node *n3 = new Node(3);
    Node *n4 = new Node(4);
    Node *n5 = new Node(5);
    Node *n6 = new Node(6);
    Node *n7 = new Node(7);
    n1->setFirst(n2);
    n1->setSecond(n3);
    n2->setFirst(n4);
    n2->setSecond(n5);
    n3->setFirst(n6);
    n3->setSecond(n7);
    //the hashmap contains all nodes and makes sure no duplicates appear, thus preventing looping over nodes in copyNode
    unordered_map<Node*, int> hashmap;
    Node * testNode = copyNode(n1, &hashmap);
    //checking the hashmap contents:
    cout << "Hashmap contents: " << endl;
    for (auto x : hashmap){
        cout << x.first << " " << x.second << endl;
    }
    //changing values of all original nodes(n1-n7):
    n1->setVal(0);
    n2->setVal(0);
    n3->setVal(0);
    n4->setVal(0);
    n5->setVal(0);
    n6->setVal(0);
    n7->setVal(0);
    cout << "Original node values after change: " << n1 -> getVal() << " ";
    cout << n1->getFirst() -> getVal()<< " ";
    cout << n1->getSecond() -> getVal()<< " ";
    cout << n1->getFirst()->getFirst() -> getVal()<< " ";
    cout << n1->getFirst()->getSecond() -> getVal()<< " ";
    cout << n1->getSecond()->getFirst() -> getVal()<< " ";
    cout << n1->getSecond()->getSecond() -> getVal() << endl;

    //checking the testNode(copy of the n1 which points to copies of all other children nodes of n1)
    cout << "Copy node values: " << testNode -> getVal() << " ";
    cout << testNode->getFirst() -> getVal()<< " ";
    cout << testNode->getSecond() -> getVal()<< " ";
    cout << testNode->getFirst()->getFirst() -> getVal()<< " ";
    cout << testNode->getFirst()->getSecond() -> getVal()<< " ";
    cout << testNode->getSecond()->getFirst() -> getVal()<< " ";
    cout << testNode->getSecond()->getSecond() -> getVal() << endl;

}
Node* copyNode (Node* node, unordered_map<Node*, int> * hashmap){
    //cout << node -> getVal() << endl;
    (*hashmap).insert({node, node -> getVal()});
    //newNodePtr points to a copy of the "node"
    Node *newNodePtr = (Node*)malloc(sizeof(Node));
    newNodePtr->setVal(node->getVal());
    if(((node -> getFirst()) != NULL) && (*hashmap).find(node -> getFirst()) == (*hashmap).end()) {
        newNodePtr->setFirst(copyNode(node->getFirst(), hashmap));
    }
    if(((node -> getSecond()) != NULL) && (*hashmap).find(node -> getSecond()) == (*hashmap).end()){
        newNodePtr->setSecond(copyNode(node->getSecond(), hashmap));
    }
    return newNodePtr;
}
int main()
{
    //lastKLinesHelper(3);
    //reverse("somestring\0");
    copyNodeHelper();
    return 0;
}
