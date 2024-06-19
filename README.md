# Swimple-Method-Channel-Android

- 請參考 `MyFlutter3Activity`，會開啟 Swimple 新增的測試頁面 `GetTokenPage`
- 以下 demo 已開出的方法 `pass PoolId` `getAccessToken` `refreshAccessToken`

## Pass PoolId

- 在 `GetTokenPage` 的 Router Path 已新增參數 `poolId`，故可以在開啟 `MyFlutter3Activity` 時，`Intent` 放入 `poolId`

（備註：目前 `BookDetailPage` 也已新增參數 `poolId`，可直接使用！）
```
startActivity(
     Intent(context, MyFlutter3Activity::class.java).apply {
            putExtra(POOL_ID, "pool111111111")
     }
)
```

- 在 `MyFlutter3Activity` 內的 `getInitialRoute` 會傳入，目標頁面的 `Router`，由此帶入 `poolId` 即可


[pool_id.webm](https://github.com/codebbkaf/android_method_channel/assets/42389238/644139dc-32f3-414a-9b02-b5a1e4c0fc82)


## getAccessToken

- 透過 `setMethodCallHandler` Handle `getAccessToken` 這個方法，內部可以實作 `取得 AccessToken` 的方法，並透過 `result.success` 傳給 Flutter 端
- Flutter 即可透過已經寫好的 util 方法 `getAccessToken()` 取得 `AccessToken`

[get_access_token.webm](https://github.com/codebbkaf/android_method_channel/assets/42389238/dc01e3c7-c3e7-4631-aafe-5285bfc9c78b)

## refreshAccessToken

- 透過 `setMethodCallHandler` Handle `refreshAccessToken` 這個方法，內部可以實作 `刷新 AccessToken` 的方法，並透過 `result.success` 傳給 Flutter 端
- Flutter 即可透過已經寫好的 util 方法 `refreshAccessToken()` 取得 `新的 AccessToken`


[refresh_access_token.webm](https://github.com/codebbkaf/android_method_channel/assets/42389238/97dabf61-a291-4339-ba25-18cbb5982f64)















