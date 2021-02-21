#!/bin/bash

OS="$(uname)"

if [ "$OS" == "Darwin" ]; then
		export CXX=/usr/local/bin/g++-10
		$CXX -std=c++14 -O3 learn_cf_ga.cpp function_to_learn_icn.cpp print.cpp -Iinclude -L/usr/local/lib -leo -leoutils -o bin/learn_cf_ga
fi

if [ "$OS" == "Linux" ]; then
		g++ -O3 learn_cf_ga.cpp function_to_learn_icn.cpp print.cpp -Iinclude -Llib -leo -leoutils -o bin/learn_cf_ga
fi
