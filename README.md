# appointments
 
Using this application you can create, add, modify or delete appoints.

To create an appointment: 
 URL: http://localhost:8080/appointments [POST]
 Request Body: Sample
               {
                "name": "Town Hall Meet",
                "purpose": "Management-Employee Meet and Greet",
                "duration": "2 hours",
                "dateTime": "27-10-2022 23:59:59.30"
              }

To Get all appointments:
 URL: http://localhost:8080/appointments [GET]
 
To Get an appointment by its id:
 URL: http://localhost:8080/appointment/{id} [GET]

To Get all appointments in a date range:
 URL: http://localhost:8080/appointments/dateRange [GET]
 Request Body: Sample
               {
                "startDate": "27-10-2022 23:59:59.30",
                "endDate": "27-10-2022 23:59:59.30"
              }

To Update an appointment:
 URL: http://localhost:8080/appointment/{id} [PUT]
 Request Body: Sample
               {
                "name": "Town Hall Meet",
                "purpose": "Management-Employee Meet and Greet",
                "duration": "2 hours",
                "dateTime": "27-10-2022 23:59:59.30"
              }

To Delete an appointment:
 URL: http://localhost:8080/appointment/{id} [DELETE]
              


All the requests and responses are logged with unique trace id.
