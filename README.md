# Task Master

## Table of Contents
* [Description](#description)
* [Link](#link)
* [Issues](#issues)
* [Resources](#resources)

### Description <a name="description"></a>
TaskMaster allows you to add a new task with a title and description. This adds a new JSON object to the database. A user can then route to `/tasks/{id}/state` to update the status of the task from "Available" -> "Assigned" -> "Accepted" -> "Finished".

### Link <a name="link"></a>
**[Deployed URL](http://taskmaster-env.3nz9fretef.us-west-2.elasticbeanstalk.com/)**

* Controllers
  * [HomeController](./src/main/java/com/nparo/TaskMaster/controllers/HomeController.java)
* Models
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

### Resources <a name="resources"></a>
* **Trevor Dobson** - fricken awesome
* Marisha Hoza
