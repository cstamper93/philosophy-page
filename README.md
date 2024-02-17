# Philosophy Page

  Motivation:

  I love consuming philosophy content and exploring different ideas, so I thought this would be a 
  great way to combine two things I love - programming and philosophy! 

  Description: 

  At a high level, this project presents different philosophers on the home page, 
  allows you to add or delete them to a list of favorites, and view the philosopher profiles.
  The profiles contain various information about the philosophers such as key ideas, 
  birthplace, and more.
  
  This is done by consuming the Philosophy API from this URL: 
  https://philosophyapi.pythonanywhere.com/api/philosophers/
  The project adds the data from this API into its own database, then keeps
  track of the user's choices on a different table.
  
  Technologies Used:
  
  * Java with SpringBoot for creating the backend, with JDBC for database connectivity;
  * PostgreSQL for utilizing relational databases to store data on philosophers 
    and user favorites;
  * Vue.js framework for displaying frontend for user interaction

# Challenges

  One of the biggest challenges I encountered was consuming the Philosophy API. The data from the /ideas 
  endpoint is given on multiple pages, which I had not encountered before. I had to figure out a way to parse 
  each page without hardcoding the page numbers while walking through the JSON.
  
  A smaller challenge was figuring out how to take the favorite button off of a philosopher
  when a user selected them, and then putting them back when they un-favorited. Ultimately, I included a 
  v-if clause on the button tag that will only display it if the philosopher is favorited.
  
  Lastly, CSS...it's very fun to play around with, but frustrating at times! That is all on that... :)
  
# Potential Future Features

* Authentication: I regret that I did not include authentication for this project, so technically everyone is just the same user and 
  nothing is kept track of for a specific user.
* Forums: once authentication is implemented, I'd love to include a place for users to be able to interact
  and discuss their take on certain philosophers and ideas. This gets at the heart of what I love about 
  philosophy - the ability to compare and contrast ideas, and learn new things from others!