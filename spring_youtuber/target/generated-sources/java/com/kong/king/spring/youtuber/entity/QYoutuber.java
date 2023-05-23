package com.kong.king.spring.youtuber.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QYoutuber is a Querydsl query type for Youtuber
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QYoutuber extends EntityPathBase<Youtuber> {

    private static final long serialVersionUID = 950363305L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QYoutuber youtuber = new QYoutuber("youtuber");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public final QMember writer;

    public final NumberPath<Long> yno = createNumber("yno", Long.class);

    public QYoutuber(String variable) {
        this(Youtuber.class, forVariable(variable), INITS);
    }

    public QYoutuber(Path<? extends Youtuber> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QYoutuber(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QYoutuber(PathMetadata metadata, PathInits inits) {
        this(Youtuber.class, metadata, inits);
    }

    public QYoutuber(Class<? extends Youtuber> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writer = inits.isInitialized("writer") ? new QMember(forProperty("writer")) : null;
    }

}

