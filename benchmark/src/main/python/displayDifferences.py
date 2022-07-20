#!/usr/bin/env python3

import sys
import pandas as pd
import statistics
from scipy.stats import mannwhitneyu

def main():
  ref_file = sys.argv[1]
  file = sys.argv[2]
  difference_type = sys.argv[3]
  displayDifferences(ref_file, file, difference_type)

def displayDifferences(ref_file, file, difference_type):
  print(difference_type)
  ref_data = pd.read_csv(ref_file, decimal=",", sep=";")
  ref_data['runtime'] = ref_data.apply (lambda row: statistics.median([row['t1'], row['t2'], row['t3'], row['t4'], row['t5']]), axis = 1)
  data = pd.read_csv(file, decimal=",", sep=";")
  data['runtime'] = data.apply (lambda row: statistics.median([row['t1'], row['t2'], row['t3'], row['t4'], row['t5']]), axis = 1)
  ref_algorithms = set(pd.unique(ref_data['algorithm']).tolist())
  algorithms = set(pd.unique(data['algorithm']).tolist())
  common_algorithms = ref_algorithms.intersection(algorithms)
  common_cases = set(pd.unique(ref_data['case']).tolist())
  print("Selected algorithms " + repr(common_algorithms))
  for algorithm in common_algorithms:
    for case in common_cases:
      ref_value = ref_data[(ref_data['algorithm'] == algorithm) & (ref_data['case'] == case)][difference_type].item()
      actual_value = data[(data['algorithm'] == algorithm) & (data['case'] == case)][difference_type].item()
      if (ref_value != actual_value):
        print("Detected " + difference_type + " difference for algorithm: " + algorithm + " on case: " + case)
        print("Reference value: " + str(ref_value))
        print("Obtained value: " + str(actual_value))

if __name__ == "__main__":
    main()