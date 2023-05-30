package com.kong.king.spring.youtuber.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostYoutuber is a Querydsl query type for PostYoutuber
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostYoutuber extends EntityPathBase<PostYoutuber> {

    private static final long serialVersionUID = 696235113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostYoutuber postYoutuber = new QPostYoutuber("postYoutuber");

    public final QPost post;

    public final NumberPath<Long> pyno = createNumber("pyno", Long.class);

    public final QYoutuber youtuber;

    public QPostYoutuber(String variable) {
        this(PostYoutuber.class, forVariable(variable), INITS);
    }

    public QPostYoutuber(Path<? extends PostYoutuber> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostYoutuber(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostYoutuber(PathMetadata metadata, PathInits inits) {
        this(PostYoutuber.class, metadata, inits);
    }

    public QPostYoutuber(Class<? extends PostYoutuber> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.youtuber = inits.isInitialized("youtuber") ? new QYoutuber(forProperty("youtuber"), inits.get("youtuber")) : null;
    }

}

