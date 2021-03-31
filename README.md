# Folders:

## learn:
Contains our main codes.
learn_cf_ga.cpp is our main program, function_to_learn_icn.cpp/hpp contain the structure and detail designs of our Interpretable Compositional Networks(ICNs), you can adjust the GA and ICNs according to your needs.

### learn/include:
Contains the EO library, you should also adjust the included GA codes in learn_cf_ga.cpp if you want to adjust GA.

### learn/spaces:
Contains the training data for our 4 behavior models:
* TOVOR_20:Training data for aggressive model, if we can't hit oppenent with punch, we keep moving forward to get close to it, when we get close enough we use punch.
* TOVOR_60:Training data for Defensive model, if we will be hit by oppenent right away, we use guard, if not, we use step back to keep away from oppenent.
* TOVOR_78:Training data for Hybrid 1(old) model, we use aggressive behavior at first, and if our HP is some lower than oppenent, we then use defensive behavior.
* TOVOR_100:Training data for Hybrid 2(new) model, we use defensive behavior at first, and if our HP is some lower than oppenent, we then use aggressive behavior.

## FightingICEv4.50:
FightingICE is a fighting game platform developed by Intelligent Computer Entertainment Lab in Ritsumeikan University in Japan, it helps researchers to build a general agent in fighting games by providing elaborate interfaces(to build parts except for AI) to let researchers concenrate on the AI parts of their agents, we use FightingICE as one of our testing games.

## behavior models:
We designed 4 behavior models(described in learn/spaces), after we trained them and got interpretable models, we implemented them as agents in FightingICE. This folder also contains the short clips for the behavior models to have intuitive images.

## extract game data:
Contains the programs aim to process the raw game data(in Json format) to get the training data we need.
And also deal the training/testing outputs to get the results.

# How to learn the utility functions:

Compile the main program to learn Utility Functions

$> cd learn

$> ./compile_learn_ga.sh

Run the program with the behavior data you want to learn 

$> ./bin/learn_cf_ga -n 9 -i spaces/The_file_you_want_to_learn

# How to build game agents in FightingICE:

## Build your AI agent:
* New a JAVA project and include AIToolKit.jar in FightingICE
* Use the learned utility functions to code the AI part using AIInterface
* Export the new jar.

## Run the agent in FightingICE:
* put the new agent jar into FightingICEv4.50/data/ai
* run the FightingICEv4.50 java application and select the new agent as player




