# Car Park Rate Calculator

A Java application for calculating parking rates based on different car park kinds and periods of stay.

<b> Author : </b>Daniel Murphy
<b> Student No. : </b>C00247818
<b>Lecturer : </b> Chris Meudec

## Overview

This application calculates the cost of parking for different car park kinds (VISITOR, MANAGEMENT, STUDENT, and STAFF) based on the normal and reduced rates during normal and reduced periods. The application takes into account various rate reductions and limits based on the car park kind.

## Features

- Calculates parking rates based on car park kind and periods of stay
- Supports VISITOR, MANAGEMENT, STUDENT, and STAFF car park kinds
- Applies rate reductions and limits based on car park kind
- 100% branch coverage with JUnit tests

## Installation

1. Clone the repository: git clone https://github.com/Daniel-Murphy33/VerificationCA


2. Import the project into your preferred Java IDE.

## Usage

1. Initialise the `Rate` class with the required parameters (normal rate, reduced rate, car park kind, normal periods, and reduced periods).
2. Call the `calculate` method with the period of stay to get the parking cost.

## Testing

1. Import the JUnit 5 test suite into your Java IDE.
2. Run the tests to verify 100% branch coverage.

## Contributing

1. Fork the repository and create your branch (`git checkout -b feature/MyNewFeature`).
2. Commit your changes (`git commit -am 'Add some feature'`).
3. Push to the branch (`git push origin feature/MyNewFeature`).
4. Create a new Pull Request.

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE](LICENSE) file for details.
