# visma_task_backend

## Build instructions
- Install intelij
- Clone this repository
- Navigate to the top right corner of the screen
- Press "Run" button

## My choises for building this web application
I wrote the back-end of this application using Java usinžg Spring boot framework. the Controller class is responsible for taking requests made by the 
front-end and executes the needed function in Service. Service calls functions from the JsonController which communicates with this applictions makeshift
database. the database is made of two json files: meetings.json, where the data about all the meeting is stored, and employees.json, where the information
about employees are stored.

## Functionality
The user of this application an add a new meeting, invite other coworkers to the newly created meeting, cancel their invitation and delete the meeting
if he doesn't want to host it anymore. Since this application has no authentication functionality the user can't be changed and is always kept logged in
as admin, however he may only cancel his own created meetings, and it's important to add that the person responsible for the meeting must attend the meeting
and cannot be removed from it.
