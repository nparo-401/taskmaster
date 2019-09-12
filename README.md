# Task Master

## Table of Contents
* [Description](#description)
* [Link](#link)
* [Issues](#issues)
* [Change Log](#change)
* [Resources](#resources)

### Description <a name="description"></a>
TaskMaster allows you to add a new task with a title and description. This adds a new JSON object to the database. A user can then route to `/tasks/{id}/state` to update the status of the task from "Available" -> "Assigned" -> "Accepted" -> "Finished".

### Link <a name="link"></a>
**[Deployed URL](http://taskmaster-env.3nz9fretef.us-west-2.elasticbeanstalk.com/)**

* Controllers
  * [HomeController](./src/main/java/com/nparo/TaskMaster/controllers/HomeController.java)
* Models
  * [History](./src/main/java/com/nparo/TaskMaster/models/History.java)
  * [Tasks](./src/main/java/com/nparo/TaskMaster/models/Tasks.java)
* Repositories
  * [DynamoDB](./src/main/java/com/nparo/TaskMaster/repository/DynamoDB.java)
  * [S3Client](./src/main/java/com/nparo/TaskMaster/repository/S3Client.java)
  * [TasksRepository](./src/main/java/com/nparo/TaskMaster/repository/TasksRepository.java)


### Issues <a name="issues"></a>
* I felt 3 major pinch points when completing this lab.
  * `load.js`. I was mostly stuck on this when needing to figure out what "faker" items to use as parameters. Trevor Dobson was able to give me a lead on how he accomplished this and it allowed me to get the data built.
  * getting the aws eb cli to install and ensuring all the keys were properly assigned. I worked with Marisha to find solutions and walk through the AWS documentation to figure out what needed to be installed locally and then initializing a user on the AWS side.
  * figuring out why the deployed site would not host properly. This was an easy fix after I realized I had not entered the security keys.
  
### Change Log <a name="change"></a>
* Day 2, team: Nick, Padma, Joachen
  * Added in an `assignee` to the task object
  * Added a new History class
  * Added a @GetMapping for the `/user/{name}/tasks` route to show the tasks assigned to a single user
  * Updated the @PutMapping for `/tasks/{id}/state` to exclude a change from "Available" to "Assigned" - this status change is handled in the route for adding an assignee
  * Added a @PutMapping for `/tasks/{id}/assign/{assignee}` to change status from "Available" to "Assigned" and to add a new instance to the list of history objects.
  * Added a `HistoryConverter` class to handle parsing the history object to an from the DynamoDB.

### Resources <a name="resources"></a>
* **Trevor Dobson** - fricken awesome
* Marisha Hoza
* [DynamoDb Type List](https://stackoverflow.com/questions/45695830/dynamodb-list-type)
