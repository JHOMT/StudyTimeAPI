create table course_types
(
    id          int auto_increment
        primary key,
    name        varchar(100) not null,
    description text         not null
);

create table courses
(
    id          int auto_increment
        primary key,
    title       varchar(255)      not null,
    description text              not null,
    type        int               not null,
    status      tinyint default 1 not null,
    constraint courses_ibfk_1
        foreign key (type) references course_types (id)
);

create index type
    on courses (type);

create table forums
(
    id          int auto_increment
        primary key,
    course_id   int          not null,
    title       varchar(255) not null,
    description text         not null,
    constraint forums_ibfk_1
        foreign key (course_id) references courses (id)
);

create index course_id
    on forums (course_id);

create table medals
(
    id          int auto_increment
        primary key,
    name        varchar(100) not null,
    description text         not null,
    criteria    text         not null
);

create table rewards
(
    id            int auto_increment
        primary key,
    name          varchar(100) not null,
    points_needed int          not null
);

create table students
(
    id           int auto_increment
        primary key,
    name         varchar(100)  not null,
    email        varchar(100)  not null,
    total_points int default 0 null,
    password     varchar(255)  not null,
    constraint email
        unique (email)
);

create table global_configurations
(
    id                        int auto_increment
        primary key,
    student_id                int                         not null,
    use_pomodoro              tinyint(1)  default 0       null,
    enable_notifications      tinyint(1)  default 1       null,
    default_pomodoro_duration int         default 25      null,
    default_break_duration    int         default 5       null,
    show_ranking              tinyint(1)  default 1       null,
    theme                     varchar(50) default 'light' null,
    constraint global_configurations_ibfk_1
        foreign key (student_id) references students (id)
);

create index student_id
    on global_configurations (student_id);

create table pomodoro_sessions
(
    id                 int auto_increment
        primary key,
    student_id         int           not null,
    session_date       date          not null,
    session_start_time time          not null,
    session_end_time   time          not null,
    pomodoro_count     int default 0 null,
    constraint pomodoro_sessions_ibfk_1
        foreign key (student_id) references students (id)
);

create index student_id
    on pomodoro_sessions (student_id);

create table student_courses
(
    student_id int               not null,
    course_id  int               not null,
    status     tinyint default 1 not null,
    primary key (student_id, course_id),
    constraint student_courses_ibfk_1
        foreign key (student_id) references students (id),
    constraint student_courses_ibfk_2
        foreign key (course_id) references courses (id)
);

create index course_id
    on student_courses (course_id);

create table student_forums
(
    id             int auto_increment
        primary key,
    student_id     int           not null,
    forum_id       int           not null,
    response       text          not null,
    points_awarded int default 0 null,
    constraint student_forums_ibfk_1
        foreign key (student_id) references students (id),
    constraint student_forums_ibfk_2
        foreign key (forum_id) references forums (id)
);

create index forum_id
    on student_forums (forum_id);

create index student_id
    on student_forums (student_id);

create table student_rewards
(
    id          int auto_increment
        primary key,
    student_id  int  not null,
    reward_id   int  not null,
    earned_date date not null,
    constraint student_rewards_ibfk_1
        foreign key (student_id) references students (id),
    constraint student_rewards_ibfk_2
        foreign key (reward_id) references rewards (id)
);

create index reward_id
    on student_rewards (reward_id);

create index student_id
    on student_rewards (student_id);

create table units
(
    id          int auto_increment
        primary key,
    course_id   int                  not null,
    title       varchar(255)         not null,
    description text                 not null,
    completed   tinyint(1) default 0 null,
    constraint units_ibfk_1
        foreign key (course_id) references courses (id)
);

create table student_medals
(
    id          int auto_increment
        primary key,
    student_id  int  not null,
    medal_id    int  not null,
    unit_id     int  not null,
    earned_date date not null,
    constraint student_medals_ibfk_1
        foreign key (student_id) references students (id),
    constraint student_medals_ibfk_2
        foreign key (medal_id) references medals (id),
    constraint student_medals_ibfk_3
        foreign key (unit_id) references units (id)
);

create index medal_id
    on student_medals (medal_id);

create index student_id
    on student_medals (student_id);

create index unit_id
    on student_medals (unit_id);

create table tasks
(
    id          int auto_increment
        primary key,
    unit_id     int                                            null,
    title       varchar(255)                                   null,
    description text                                           not null,
    type_task   enum ('QUIZ', 'ASSIGNMENT', 'PROJECT', 'EXAM') not null,
    due_date    date                                           null,
    points      int default 0                                  null,
    constraint tasks_ibfk_1
        foreign key (unit_id) references units (id)
);

create table student_tasks
(
    id              int auto_increment
        primary key,
    student_id      int                                                                                                                   not null,
    task_id         int                                                                                                                   null,
    completion_date date                                                                                                                  null,
    importance      enum ('URGENT_AND_IMPORTANT', 'NOT_URGENT_BUT_IMPORTANT', 'URGENT_BUT_NOT_IMPORTANT', 'NOT_URGENT_AND_NOT_IMPORTANT') not null,
    is_completed    tinyint(1) default 0                                                                                                  null,
    points_awarded  int        default 0                                                                                                  null,
    constraint student_tasks_ibfk_1
        foreign key (student_id) references students (id),
    constraint student_tasks_ibfk_2
        foreign key (task_id) references tasks (id)
);

create index student_id
    on student_tasks (student_id);

create index task_id
    on student_tasks (task_id);

create index unit_id
    on tasks (unit_id);

create table unit_materials
(
    id              int auto_increment
        primary key,
    unit_id         int                                     not null,
    material_type   enum ('DOC', 'VIDEO', 'AUDIO', 'IMAGE') not null,
    name            varchar(255)                            not null,
    material_base64 text                                    not null,
    uploaded_at     timestamp default current_timestamp()   null,
    description     varchar(255)                            not null,
    constraint unit_materials_ibfk_1
        foreign key (unit_id) references units (id)
);

create index unit_id
    on unit_materials (unit_id);

create index course_id
    on units (course_id);