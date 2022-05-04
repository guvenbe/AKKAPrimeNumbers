package com.vp;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehavior extends AbstractBehavior<String> {


    private FirstSimpleBehavior(ActorContext context) {
        super(context);
    }

    public static Behavior<String> create(){
        return Behaviors.setup(FirstSimpleBehavior::new);
    }

    @Override
    public Receive createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("say hello",() -> {
                    System.out.println("Hello");
                    return this;
                })
                .onMessageEquals("who are you?",() -> {
                    System.out.println("My path is : " + getContext().getSelf().path());
                    return  this;
                })
                .onAnyMessage(message -> {
                    System.out.println("I received the message : " + message);
                    return this;
                })
                .build();
    }
}
