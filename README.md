# Folders:

## learn:
Contains our main code.
learn_cf_ga.cpp is our main program, function_to_learn_icn.cpp/hpp contain the structure and detail designs of our Interpretable Compositional Networks (ICN), you can adjust the GA and ICNs according to your needs.

### learn/include:
Contains the EO library, you should also adjust the included GA codes in learn_cf_ga.cpp if you want to adjust GA.

### learn/spaces:
Contains the training data of 4 behavior models:
* TOVOR_20: Dataset for aggressive model. If we can't hit opponent with punch, we keep moving forward to get closer. If we are close enough, we punch.
* TOVOR_60: Dataset for Defensive model. If we will be hit by opponent right away, we use guard, if not, we use step back to keep away from opponent.
* TOVOR_78: Dataset for Hybrid 1 (old version) model, we use aggressive behavior at first, and if our HP is some lower than opponent, we then use defensive behavior.
* TOVOR_100: Dataset for Hybrid 2 (new version) model, we use defensive behavior at first, and if our HP is some lower than opponent, we then use aggressive behavior.

## FightingICEv4.50:
FightingICE  is  a fighting  game  platform  developed by  Intelligent
Computer Entertainment Lab in Ritsumeikan University in Japan.  We use
this platform as a test-bed of our method.

## "behavior models":
Contains the source code of  our FightingICE agents reproducing target
behaviors from the  learn/spaces folder. It also  contains short video
clips for each targe behavior.

## "extract game data":
Contains the JAVA  program processing raw game data  (in JSON format),
to get training/test datasets.

# How to learn the utility functions:

Compile the main program to learn Utility Functions

$> cd learn

$> ./compile_learn_ga.sh

Run the program with the behavior data you want to learn 

$> ./bin/learn_cf_ga -n 9 -i spaces/The_file_you_want_to_learn

# How to build game agents in FightingICE:

## Build your AI agent:
* Create a new JAVA project and include AIToolKit.jar in FightingICE,
* Use the learned utility functions to code the AI part using AIInterface,
* Export a new jar file.

## Run the agent in FightingICE:
* Put the new jar file into FightingICEv4.50/data/ai,
* Run FightingICEv4.50 and select the new agent as a player.
