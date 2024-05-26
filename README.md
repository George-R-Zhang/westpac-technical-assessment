## westpac-technical-assessment

# Intro

Welcome and thank you for considering me for this assessment opportunity. I'm excited to take on the
challenge!

The task for this assessment involves building a simple app that fetches data
from https://random-data-api.com/api/v2/credit_cards?size=100. Below are the strategies I've adopted
to tackle this:

1. Architecture: Employing MVVM alongside Clean Architecture principles.
2. Technologies: Utilizing Jetpack Compose, Coroutine, Ktor, and Hilt.
3. Testing: Employing JUnit4 and Mockito for comprehensive testing.

# Notes

1. State Management: The application encompasses four distinct states: Initial, Loading, ShowCards,
   and FailToLoad. Each state corresponds to a specific view, with the Initial state serving as the
   starting point of the view.
2. Data Fetching Optimization: Despite the API requesting a size of 100, I have implemented a
   mechanism to fetch data in smaller increments, retrieving 20 records per fetch to mitigate memory
   usage.
3. Handling API Responses: While testing, I discovered that the API could respond with 'Retry Later'
   if the request is made too quickly. I've designated this scenario as the endpoint of the request
   and implemented measures to prevent subsequent calls from users.
4. Flexibility in Data Processing: At this stage, I haven't performed any data conversion. However,
   for instance, the 'CreditCardInfo' class could implement a sealed interface if additional data
   types (like debit cards) become available.
5. Modularized Request Layer: I prioritize maintaining a clean request layer, so I break down the
   request message into parts. The 'CardEngine' triggers tasks in the domain, such as '
   getCreditCards', by providing card type and size parameters (e.g., credit_cards?size=100). The '
   MessageManager' combines these parts and sends the message, storing it
   at https://random-data-api.com/api/v2/. Eventually, the combined message is sent to 'ApiRemote'
   to fetch the response.
6. Testing Approach: Although I've included some unit tests, the simplicity of this assessment makes
   it challenging to create comprehensive tests due to the lack of complex business logic.
   Typically, these tests would be essential for validating logic in real-world scenarios.
7. UI Testing: The android test is also quite simple at this stage. Basically check for each state
   if the screen shows correctly

