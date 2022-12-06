# Faircorp

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## This is the Backend project for faircorp


### It has 4 entities 
- Buildings
- Rooms
- Windows
- Heaters

### The website is available [here](https://mahditrabolsi.cleverapps.io)

### The API is available [here](https://mahditrabolsi.cleverapps.io/api)

API documentation
-----------------

### Building

- GET /api/buildings : get all buildings
- GET /api/buildings/{id} : get a building by id
- POST /api/buildings : create a building
- DElETE /api/buildings/{id} : delete a building by id

### Room

- GET /api/rooms : get all rooms
- GET /api/rooms/{id} : get a room by id
- POST /api/rooms : create a room
- DELETE /api/rooms/{id} : delete a room by id
- PUT /api/rooms/{id}/switchHeaters : switch heaters of a room by id
- PUT /api/rooms/{id}/switchWindows : switch windows of a room by id
- GET /api/rooms/building/{buildingId} : get all rooms of a building by id

### Window

- GET /api/windows : get all windows
- GET /api/windows/room/{roomId} : get all windows of a room by id
- POST /api/windows : create a window
- DELETE /api/windows/{id} : delete a window by id
- PUT /api/windows/{id}/switch : switch a window by id

### Heater

- GET /api/heaters : get all heaters
- GET /api/heaters/room/{roomId} : get all heaters of a room by id
- POST /api/heaters : create a heater
- DELETE /api/heaters/{id} : delete a heater by id
- PUT /api/heaters/{id}/switch : switch a heater by id


