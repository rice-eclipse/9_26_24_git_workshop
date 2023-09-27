import random

def generate_random_numbers(n=10, lower_bound=1, upper_bound=100):
    """Generate a list of n random integers between lower_bound and upper_bound."""
    return [random.randint(lower_bound, upper_bound) for _ in range(n)]

def main():
    # Generate a list of 10 random numbers between 1 and 100
    numbers = generate_random_numbers()

    # Calculate the sum of the numbers
    total = sum(numbers)

    # Print the numbers and their sum
    print(f"Random Numbers: {numbers}")
    print(f"Sum: {total}")

if __name__ == "__main__":
    main()
