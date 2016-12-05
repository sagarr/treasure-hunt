---------------------------------------------------
Treasure Hunt - 

A specification for simple treasure hunt game with clue stored in game.dat. 
There is an associated index file named index.dat which provides meta-data to the data inside the flat game.dat file. 

There are 2 files
- Index file
	- Index file is divided into 10 bytes long
	- Structure of each index is like below - 
		 1 2     3
	    |_|_|________|
	- it has 3 parts
		- 1st part - 1 byte long - tell whether its hex code OR base 64 encoding data, 0 - hex, 1 - base 64
		- 2nd part - 1 byte long - tell whether index points to more than 4 records, 0 - no, 1 - yes 
		- 3rd part - 8 bytes long - each 2 byte will tell u record index, (so max 4 records are pointed by single index) if 2nd byte is true (means set to 1), then last 2 bytes of this part will point to next index in index file

- Records file
	- Each record is 64 byte long
	- Either hex or base 64 encoded
	- Once you decode record, it will give you some clue which will point you to next index to read

Using your favorite programming language, you have to write a program which will take you thru all the clues and eventually to the final treasure....

To start playing game, you have to read index 444

---------------------------------------------------

Rules
- Its programming assignment & you have to pair with someone 
- Don't share your clues with others
- You have to print records output to console in order to convince us that you actually have all clues
- Internet on mobile allowed
- First one who crack last piece of treasure hunt will win..
- And there is no one to answer your questions, doubts.

---------------------------------------------------