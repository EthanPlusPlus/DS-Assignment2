import matplotlib.pyplot as plt
import numpy as np;

with open('instruments.txt', 'r') as file:
    lines = file.readlines()

def log_function(x):
    return np.log(x)

group1_circle = []
group2_circle = []
group1_square = []
group1_cross = []
group2_square = []
group2_cross = []

for i in range(0, 10):
       group1_circle.extend([int(lines[i])]) #insert
       group2_circle.extend([int(lines[10 + i])])
       group1_square.extend([int(lines[20 + i])])
       group1_cross.extend([int(lines[30 + i])])
       group2_square.extend([int(lines[40 + i])])
       group2_cross.extend([int(lines[50 + i])])

x = [1, 10, 55, 100, 550, 1000, 5500, 10000, 30000, 50000]

x_log = np.linspace(1, 50000, 500)  # Adjust the range of x values as needed
y_log = log_function(x_log)

plt.plot(x, group1_circle, color='red', marker='o', label='Insert Average Case')

plt.plot(x, group2_circle, color='blue', marker='o', label='Search Average Case')

plt.plot(x_log, y_log, color='green', label='Log Function: y = log(x)')

plt.scatter(x, group1_square, color='red', marker='s', label='Insert Best Case',alpha=0.5)

plt.scatter(x, group1_cross, color='red', marker='x', label='Insert Worst Case',alpha=0.5)

plt.scatter(x, group2_square, color='blue', marker='s', label='Search Best Case',alpha=0.5)

plt.scatter(x, group2_cross, color='blue', marker='x', label='Search Worst Case',alpha=0.5)


plt.xlabel('n')
plt.ylabel('Operations')
plt.title('Graph of Insert and Search Time Complexity in AVL Tree')
plt.legend(loc='upper right')  

plt.grid(True)
plt.show()