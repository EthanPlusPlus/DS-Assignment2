import matplotlib.pyplot as plt

# Read data from the text file
with open('instruments.txt', 'r') as file:
    lines = file.readlines()

# Initialize lists for each group
group1_circle = []
group2_circle = []
group1_square = []
group1_cross = []
group2_square = []
group2_cross = []

# Extract data for each group
for i in range(0, 10):
       group1_circle.extend([int(lines[i])])
       group2_circle.extend([int(lines[10 + i])])
       group1_square.extend([int(lines[20 + i])])
       group1_cross.extend([int(lines[30 + i])])
       group2_square.extend([int(lines[40 + i])])
       group2_cross.extend([int(lines[50 + i])])

# Define x values
x = [1, 10, 55, 100, 550, 1000, 5500, 10000, 30000, 50000]

# Plot points for group 1 (circle)
plt.plot(x, group1_circle, color='red', marker='o', label='Group 1 Circle Points')

# Plot points for group 2 (circle)
plt.plot(x, group2_circle, color='blue', marker='o', label='Group 2 Circle Points')

# Plot points for group 1 (square)
plt.scatter(x, group1_square, color='red', marker='s', label='Group 1 Square Points',alpha=0.5)

# Plot points for group 1 (cross)
plt.scatter(x, group1_cross, color='red', marker='x', label='Group 1 Cross Points',alpha=0.5)

plt.scatter(x, group2_square, color='blue', marker='s', label='Group 1 Square Points',alpha=0.5)

# Plot points for group 1 (cross)
plt.scatter(x, group2_cross, color='blue', marker='x', label='Group 1 Cross Points',alpha=0.5)

# Add labels and legend
plt.xlabel('X-axis')
plt.ylabel('Y-axis')
plt.title('Data from Text File')
plt.legend(loc='upper right')  # Move legend to upper left corner

# Show plot
plt.grid(True)
plt.show()