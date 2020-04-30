# CUPrk

## TODO
Make this readme good


### When to update number of spaces in a lot

* User is in a geofence
	* Easy, just use Radar
* User is a car
	* ActivityRecognitionClient recognizes when phone is walking, driving, etc
* Distinguish between passengers and drivers
	* Notification popup?

#### When does a lot decrease number of spots available?

1. App detects user is driving
2. Distinguish betweenm driver and passenger(s)
3. Set radar user metadata to driving state
4. User enters a geofence
5. User's database entry is updated to show most recent event if user is driving (only update if driving)
6. User exits geofence
7. If user's most recent database entry is the user entering that geofence, decrement spots available for that lot
