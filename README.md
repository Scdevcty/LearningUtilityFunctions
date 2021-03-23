# Introduction:
learn folder contains the ICN and GA codes in our project.

FightingICEv4.50 folder is one of our testing game, we build the game agent in this game.

Experiment data processing codes folder contains the data processing programs to get the training data from the raw game data, and process the training/testing outputs to get results.

4 behavior models of our experiments folder contains the 4 agent codes implemented using our training results in experiments.

Please have more details about them in the ReadMe files in their own folders.

# How to use:

Compile the main program to learn Utility Functions

$> cd learn

$> ./compile_learn_ga.sh

Run the program with the behavior data you want to learn 

$> ./bin/learn_cf_ga -n 9 -i spaces/The_file_you_want_to_learn



