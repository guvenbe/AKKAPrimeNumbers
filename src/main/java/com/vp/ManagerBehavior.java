package com.vp;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ManagerBehavior extends AbstractBehavior {
    private ManagerBehavior(ActorContext context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(ManagerBehavior::new);
    }

    @Override
    public Receive createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("start", () -> {
                    for (int i = 0; i < 20; i++) {
                        ActorRef<WorkerBehavior.Command> worker = getContext().spawn(WorkerBehavior.create(), "Worker" + i);
                        worker.tell(new WorkerBehavior.Command("start",getContext().getSelf()));
                    }
                    return this;
                })
                .build();
    }
}
