# Car Rental App

This is a simple Java console application for managing a car rental business. It allows you to add, edit, search, rent, and return cars, as well as manage car data in a CSV file.

## Features

- **Add Car**: Add a new car to the rental pool by entering its details.
- **Edit Car**: Update a car's id, make, model, year, or type.
- **Remove Car**: Mark a car as removed so it cannot be rented anymore.
- **List Cars**: List all available cars or all cars (including rented and removed).
- **Rent Car**: Rent a car to a customer, specifying the rental start and estimated return dates (in DD-MM-YYYY format). Cars with status "Removed" cannot be rented.
- **Return Car**: Return a rented car, making it available again.
- **Search**: Search for cars by any field (id, make, model, year, type, or status) using a search term.
- **Data Persistence**: All car data is stored in a CSV file. Changes are saved when you exit the app.
- **User-Friendly Input**: The app is forgiving to spaces in input and enforces correct date formats.

## How to Use

1. Run the application. You will see a list of available commands.
2. Enter commands as prompted. For example:
   - `Add` to add a car
   - `Edit <id>` to edit a car's details
   - `Rent <id>` to rent a car
   - `Return <id>` to return a car
   - `Remove <id>` to remove a car
   - `List` to list available cars
   - `List all` to list all cars
   - `Search` to search for cars
   - `Exit` to save and quit
3. Follow the prompts for each command. Input is case-insensitive and forgiving to spaces after commas.

## Requirements
- Java 8 or higher

## Project Structure
- `CarRentalApp.java` - Main entry point
- `managers/` - User interaction and command handling
- `models/` - Car data model
- `services/` - Business logic and file I/O
- `interfaces/` - Interfaces for rental and search functionality
- `cars.csv` - Car data storage

## Notes
- The app enforces date input in DD-MM-YYYY format for rentals.
- Removed cars cannot be rented.
- All changes are saved to `cars.csv` on exit.


