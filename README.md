# Vending Machine Simulation

## Project Overview

This Java-based Vending Machine Simulation project emulates the functionality of a real-world vending machine. It's designed to demonstrate object-oriented programming principles, file I/O operations, and basic currency handling in Java.

## Features

- **User-friendly Menu Interface**: Navigate through options to view items, make purchases, and complete transactions.
- **Dynamic Inventory Management**: Load and manage inventory from a CSV file.
- **Currency Handling**: Process money input and calculate change in coins.
- **Transaction Logging**: Keep detailed logs of all transactions for auditing purposes.
- **Sales Reporting**: Generate sales reports for business analysis.

## Project Structure

- `Application.java`: Main entry point of the application.
- `VendingMachine.java`: Core class that manages the vending machine operations.
- `Menu.java`: Handles user interface and interaction.
- `Inventory.java`: Manages loading and creation of inventory items.
- `Slot.java`: Represents a slot in the vending machine.
- `Cashier.java`: Handles change calculation and formatting.
- `VendLog.java`: Manages transaction logging and report generation.
- `Item.java`: Abstract base class for vending machine items.
- `Candy.java`, `Chip.java`, `Drink.java`, `Gum.java`: Specific item classes.

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone this repository to your local machine.
3. Navigate to the project directory in your terminal.
4. Run the application:
   ```
   java com.techelevator.Application
   ```

## Usage

1. When prompted, select from the following options:
   - Display Vending Machine Items
   - Purchase
   - Exit
2. If purchasing, you can:
   - Feed Money
   - Select Product
   - Finish Transaction
3. Follow the on-screen prompts to complete your transaction.

## Configuration

- Inventory is loaded from `vendingmachine.csv` by default.
- Logs are stored in the `logs` directory.

## Contributing

Contributions to improve the Vending Machine Simulation are welcome. Please feel free to fork the repository and submit pull requests.

## License

This project is open source and available under the [MIT License](LICENSE).
