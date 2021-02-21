#pragma once

#include <vector>
#include <functional>

using namespace std;

constexpr int number_units_specification = 28;
constexpr int number_units_combination = 4;
constexpr int number_units_constant = 5;
constexpr int number_actions = 4;


double g( const vector<int>& weights,
          const vector<int>& vars,
          int start,
          int nb_vars );
