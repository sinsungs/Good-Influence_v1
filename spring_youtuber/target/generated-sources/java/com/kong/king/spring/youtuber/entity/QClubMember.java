package com.kong.king.spring.youtuber.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMember is a Querydsl query type for ClubMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubMember extends EntityPathBase<ClubMember> {

    private static final long serialVersionUID = -310538390L;

    public static final QClubMember clubMember = new QClubMember("clubMember");

    public final StringPath email = createString("email");

    public final BooleanPath fromSocial = createBoolean("fromSocial");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final SetPath<ClubMemberRole, EnumPath<ClubMemberRole>> roleSet = this.<ClubMemberRole, EnumPath<ClubMemberRole>>createSet("roleSet", ClubMemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public QClubMember(String variable) {
        super(ClubMember.class, forVariable(variable));
    }

    public QClubMember(Path<? extends ClubMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubMember(PathMetadata metadata) {
        super(ClubMember.class, metadata);
    }

}

