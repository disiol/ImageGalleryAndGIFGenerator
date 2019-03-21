
    Від тестувальника - 
    - коли юзер вводить символ тільки в e-mail або тільки в password fields і натискає accept - додаток повертає помилку: fields cannot be empty
    - некоректна валідація імейлу на login page
    - коли юзер обирає фото - напис не зникає
    - на sign up page - коли юзер вибирає фото більше 2 мб - фото не загружається і немає ніякого error message
    - незрозумілий error - incorrect email or password - create the new user
    - немає logout button
    - коли немає зображень юзер може натиснути play button
    валідація на розмір тільки 2 мб
    юзеру не потрібно бачити весь текст помилки: {“children”:{“image”...
    коли юзер додає фото і натискає back button - юзер може додати знову ту ж фото
    logout відбувається без підтвердження коли юзер тисне back button
    
    Oksana Puzik, [12.03.19 11:01]
    Від розробника:
    - no architecture at all
    - every new screen has it's own API client wrapper (should be singleton)
    - too big methods (found one for 200 lines, very hard to maintain such code)
    - Picasso and Glide both included (serve same purpose, no reason to use both of this libraries in one project)
    - a lot of possible memory leaks (capturing context and views in callbacks, without even canceling/clearing them)
    - storing single data (such as token) in DB