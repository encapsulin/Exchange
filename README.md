Populating the Exchange with random Orders

send(225587, true, 1, 94);

id	| Buy/Sell	| Price	| Size
--------------------------------------
225587	| Buy		| 1	| 94

send(715336, false, 10, 50);

id	| Buy/Sell	| Price	| Size
--------------------------------------
225587	| Buy		| 1	| 94

715336	| Sell		| 10	| 50

send(57805, true, 18, 95);
lowestPiceOrder:orderId:715336,isBuy:false,Price:10,Size:50

id	| Buy/Sell	| Price	| Size
--------------------------------------
225587	| Buy		| 1	| 94

715336	| Sell		| 10	| 0

57805	| Buy		| 18	| 45

send(340308, false, 14, 47);
bestPiceOrder:orderId:57805,isBuy:true,Price:18,Size:45

id	| Buy/Sell	| Price	| Size
--------------------------------------
225587	| Buy		| 1	| 94

715336	| Sell		| 10	| 0

57805	| Buy		| 18	| 0

340308	| Sell		| 14	| 2

send(90119, true, 15, 47);
lowestPiceOrder:orderId:340308,isBuy:false,Price:14,Size:2

id	| Buy/Sell	| Price	| Size
--------------------------------------
225587	| Buy		| 1	| 94

715336	| Sell		| 10	| 0

57805	| Buy		| 18	| 0

340308	| Sell		| 14	| 0

90119	| Buy		| 15	| 45
#################
getLowestSellPrice: 0
getHighestBuyPrice: 15
getTotalSizeAtPrice(0): 0
getTotalSizeAtPrice(1): 94
getTotalSizeAtPrice(2): 0
Done.
