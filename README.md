# CS321-Balloon-Stabilization
Stabilization and pointing software for Aero-HAB 3

In the Stabilization Software's src folder you will find the folders:
  - basicTCP, which contains our rudimentary single-threaded TCP server and client test code.
  - octoberTestFlightSoftware, which contains a basic logger that pulls gyroscope data from a TCP server for a period of two hours to test the reliability of our communications system during the flight.
  - finalFlightSoftware, which contains the full stabilization suite which includes the ability to be remotely shut down and change the heading of the balloon
