#pragma once

#include <vector>
#include <functional>

using namespace std;

constexpr int number_units_specification = 30;
constexpr int number_units_combination = 4;
constexpr int number_units_constant = 5;


double g( const vector<int>& weights,
          const vector<int>& vars,
          int start,
          int nb_vars );
