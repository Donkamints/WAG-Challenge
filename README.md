# WAG-Challenge
A small project that deals with loading JSON data and Gravatar profile images.

The profile images are loaded into a RecyclerView to make sure the application uses memory as best as it can. As the number of profiles
grow the Recycler will be able to handle it. That is why I chose it for this project. Using a list view would have worked just fine but
its not a good choice for large scale projects. I really wanted to use the same custom fonts as WAG! does and add a splash of their color
scheme as well. The application took a bit of a performance hit using the custom TrueTypeFonts but I think it was worth it as it ties
into the theme of the company. The profiles are sorted by number of gold badges. I tried to come up with a better scheme for sorting them
but it seemed as I wanted to get other details put into the project I just setteled on this for now. Thinking on it again I may have done
something like percentage of overall badges awarded leaning toward gold. Such as the person who recived the most gold badges out of their
total badge count would likely be the proile with the best performance. This isn't always the case but its a good marker for performance.

The RecyclerView is declared static. The reason for this is when the user goes from landscape to portrait mode they
would lose the posistion in the RecyclerView. All of the things I attempted with setting a empty adapater in onCreate and calling notifyDataSetChanged() after the data had been loaded cause the same problem. I feel if you were scrolling through a list of something and changed
from portrait to landscape your scrollposition should be retained.  The compiler was giving me a warning not to declare the RecyclerView static but I felt I had no other option to maintain functionality of the application. In hindsight I think there is a function called scrollToPosition() that you can pass the position to in like a savedInstance state or something when it flips and it will auto scroll you back to where you left off.

The only third party lib I used was Piccaso for the gravatar Image loading and it is available here 
http://square.github.io/picasso/ 

I enjoyed this project and I certainly learned quite a bit while doing it. I hope you enjoy it as much as I did working on it.
