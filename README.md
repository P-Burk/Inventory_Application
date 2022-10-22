# Inventory Application

## • Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
The requirements for this app are that it meets the needs of a user trying to keep track of their inventory. This requires the app to allow a user to login to their inventory, add items to the inventory, remove items from the inventory, and alter the counts of items in the inventory. My goal was to satisfy all these needs via the use of a simple and easy to use interface that other inventory tracking apps were lacking.
## • What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
To meet user needs it was necessary to create a login screen, inventory screen, new item screen, and a settings screen. The designs I used for these screens were made with users in mind through my goal of keeping the user interfaces simple and easy to navigate. I feel that my designs are successful in meeting this goal because the overall layout of the app is simpler than other inventory apps that I had looked at. In my app, it takes less clicks/taps to perform the "core" actions of an inventory such as adding an item, deleting an item, and modifying an item's count.
## • How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?
I approached coding this application by first creating a Project board on Github for my repository. From here I created issues for all the different elements/functions I wanted to implement. Within each issue I added the parts that I felt would be needed to complete the feature. For example, for the SMS option feature, I had noted that some sort of `SMSenabled` flag should be used and to change this flag with the switch in the settings activity. In my note I said that perhaps a global variable could be used, but ended up going with a variable belonging to a User object and stored in the Users table in the database. Once I felt I had added all the features as issues on my Github project board, I grouped them how I felt they should be implemented. From here, I had a list of issues/features to work on and a way to track my progress with the overall completion of coding the app.
## • How did you test to ensure your code was functional? Why is this process important and what did it reveal?
I tested my code functionality through using the app on an emulated Android device via the built-in emulator in Android Studio. This process is important because it allows you to use your app to test features/changes as you go. It wouldn't make much sense to code the entirety of your app, then push it to your physical device to test it afterwards. The built-in emulator made it so that I could A) see the layout of my screens/activities and B) test any changes and features to ensure they functioned as I had intended. Testing via using the app in the emulator revealed to me that I needed some way to set a "current user" so that I could track things like the user's SMS flag or phone number when using the SMS notification feature or changing the user's phone number and/or changing their SMS notification settings in the settings activity.
## • Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?
One major challenge that I faced was getting the user's phone number to "follow them" through different screens/activities. For example, I had not implemented a way for the app to differentiate who was actually logged into the app. On the login screen, the login button simply checked if the username/password combination was in the database and then when to the inventory screen if it was. From here, if you went to the settings screen to turn on/off SMS notifications or update the user's phone number, there was no way the app knew which user in the database to update their number or whether a user had their SMS notifications enabled or disabled. To overcome this, I implemented a constant variable on each activity that would hold the current user's username. This made it so that you could call the database helper functions and have a key to pass for the "where" clauses in the database queries. Upon implementing this, I quickly figured out that I needed to learn about passing data between activities, which resulted in me implementing things such as `putExtra()` and activity result launchers to meet this need.
## • In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
I feel that the recyclerView and the cards that make up my inventory screen are demonstrations of the knowledge, skills, and experienced gained during this class. The recyclerView wasn't particularly hard to implement, but getting all of the interactions that a user can do with the cards such as altering item counts via buttons and/or editing the count text and deleting items from the inventory using the trash/delete button took a lot of learning to implement correctly. Pairing that with CRUD functions for the database so that these changes could be stored was both challenging and rewarding once I finally got it all implemented and tied together.
