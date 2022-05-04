package com.vp;

import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehavior.create(), "FirstActorSystem");
        actorSystem.tell("Hello are you there?");
        actorSystem.tell("This is the second message");
        actorSystem.tell("say hello");
        actorSystem.tell("who are you?");
    }
}
