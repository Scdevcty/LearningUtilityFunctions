# Introduction:
learn folder contains the main program in our project.

FightingICEv4.50 folder is one of our testing game, we build the game agent in this game.

JsonToTXT folder is the data processing program to get the training data from the raw game data.

data folder contains the training data we use for different behavior patterns.


Please have more details about them in the ReadMe files in their own folders.

# How to use:

Compile the main program to learn Utility Functions

$> cd learn

$> ./compile_learn_ga.sh

Run the program with the behavior data you want to learn 

$> ./bin/learn_cf_ga -n 9 -i spaces/The_file_you_want_to_learn



