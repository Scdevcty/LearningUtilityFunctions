#!/bin/bash

export CXX=/usr/local/bin/g++-10
$CXX -std=c++14 learn_cf_ga.cpp ./function_to_learn_icn.cpp ./print.cpp -I./include -L/usr/local/lib -leo -leoutils -o bin/learn_cf_ga
