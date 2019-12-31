import ast
import os
import sys
from os.path import abspath

from pprintast import pprintast as ppast, Mode


# Author: Connor Woodahl
# Assignment Title: Evaluations Assignment BaylorIRES 2020
# Assignment Description: This program produces an abstract syntax tree from all found .py files
# in a given directory and all subdirectories
# Due Date: 12/31/2019
# Date Created: 12/20/2019
# Date Last Modified: 12/30/2019

# Main
# This is the driver that will implement the Project's intended description.

def main():
    # gets inputted directory to search for py files
    # the path is the first argument given in the command line arguments
    path = sys.argv[1]

    # gets .py files in given directories and subdirectories
    pyfiles = [os.path.join(root, name)
               for root, dirs, files in os.walk(path)
               for name in files
               if name.endswith((".py"))]

    # for all .py files found
    for filename in pyfiles:
        # gets the path of the file in the pyfile list
        full_path = abspath(filename)

        # creates a new file with the file extension equal to the .py file found plus the .ast extension
        with open(full_path, "r") as source:
            tree = ast.parse(source.read())
            a = open(full_path + ".ast", "w")
            sys.stdout = a
            ppast(tree, full_path + ".ast", Mode.EXEC, False, False, "  ")

            # For testing the ast and visualizing it
            # f = open(full_path + ".txt", "w")
            # sys.stdout = f
            # ppast(tree, full_path + ".txt", Mode.EXEC, False, False, "  ")

# python way of utilizing the main method
if __name__== "__main__":
  main()
