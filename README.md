# visma_task_backend

## Build instructions
- Install InteliJ IDEA
- Clone this repository
- Navigate to the top right corner of the screen
- Press "Run" button

## Technology stack
- Java Spring Boot framework
Controller class is responsible for taking requests made by the 
front-end and executes the needed function in Service. Service calls functions from the JsonController which communicates with this applictions mocked
database. Database is made of two json files: [meetings.json](https://github.com/karolispranas/visma_task_backend/blob/main/meetings.json),
where the data about all the meeting is stored, and [employees.json](https://github.com/karolispranas/visma_task_backend/blob/main/employees.json),
where the information about employees is stored.

## Functionality
This is a booking application for meetings. User of this application can add a new meeting,
invite other coworkers to the newly created meeting, cancel their invitation and delete the meeting
if he doesn't want to host it anymore. Since this application has no authentication functionality the user can't be changed and is always kept logged in
as admin, however he may only cancel his own created meetings, and it's important to add that the person responsible for the meeting must attend the meeting
and cannot be removed from it.
