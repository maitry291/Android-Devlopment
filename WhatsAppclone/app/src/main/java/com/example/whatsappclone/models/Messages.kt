package com.example.whatsappclone.models

import java.sql.Timestamp

class Messages() {
    var uId:String?=""
    var message:String?=""
    var timestamp:String?=""
    //we need receiver id to delete any message as we have to get child of id senderid+receiver id.
    var receiverId:String?=""
    //to delete msg we need that msg id too
    var messageId:String?=""
    //to know the name of receiver in group chat
    var name:String?=""

    constructor(uId:String?,message:String?,timestamp:String?):this()
    constructor(uId:String?,message:String?):this()

}