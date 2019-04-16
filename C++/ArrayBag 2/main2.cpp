#include <iostream> // For cout and cin
#include <string>   // For string objects
#include "Bag.h"    // For ADT bag
using namespace std;

void displayBag(Bag<string>& bag)
{
	cout << "The bag contains " << bag.getCurrentSize()
        << " items:" << endl;
   vector<string> bagItems = bag.toVector();
   
   int numberOfEntries = (int) bagItems.size();
   for (int i = 0; i < numberOfEntries; i++)
   {
      cout << bagItems[i] << " ";
   }  // end for
	cout << endl << endl;
}  // end displayBag



int main()
{
   string clubs[] = { "Joker", "Ace", "Two", "Three",
      "Four", "Five", "Six", "Seven",
      "Eight", "Nine", "Ten", "Jack",
      "Queen", "King" };
   
   // Create our bag to hold cards.
   Bag<string> grabBag;
   Bag<string> grabBag2;
   Bag<string> ubag;

   // Place six cards in the bag.
   grabBag.add(clubs[1]);
   grabBag.add(clubs[2]);
   grabBag.add(clubs[4]);
   grabBag.add(clubs[8]);
   grabBag.add(clubs[10]);
   grabBag.add(clubs[12]);
   
   //Place 5 cards into second bag
   grabBag2.add(clubs[1]);
   grabBag2.add(clubs[0]);
   grabBag2.add(clubs[10]);
   grabBag2.add(clubs[11]);
   grabBag2.add(clubs[12]);
 
   // ubag = grabBag.combine(grabBag2);
   displayBag(ubag);
   // ubag = grabBag.intersection(grabBag2);
   displayBag(ubag);
   // ubag = grabBag.diff(grabBag2);
   displayBag(ubag);
 
   return 0;
}; // end main

