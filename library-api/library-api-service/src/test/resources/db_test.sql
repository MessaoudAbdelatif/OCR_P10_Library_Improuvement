
--
-- Name: book; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.book (
    id bigint NOT NULL,
    author character varying(40) NOT NULL,
    cover_pic_url character varying(255),
    language character varying(50),
    name character varying(50) NOT NULL,
    overview character varying(255),
    publication_date timestamp without time zone NOT NULL,
    publisher character varying(255) NOT NULL,
    subject character varying(255),
    borrow_id bigint,
    library_id bigint,
    stock_id bigint
);


--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;


--
-- Name: booking; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.booking (
    bookid bigint NOT NULL,
    library_userid character varying(255) NOT NULL,
    date_creation date NOT NULL,
    date_of_closing date,
    is_closed boolean NOT NULL,
    is_notified boolean
);


ALTER TABLE public.booking OWNER TO admin_db_library;

--
-- Name: borrow; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.borrow (
    id bigint NOT NULL,
    closed boolean NOT NULL,
    date_end timestamp without time zone,
    date_start timestamp without time zone,
    extra_time boolean NOT NULL,
    bookid_id bigint,
    user_id character varying(255)
);


ALTER TABLE public.borrow OWNER TO admin_db_library;

--
-- Name: borrow_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.borrow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.borrow_id_seq OWNER TO admin_db_library;

--
-- Name: borrow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.borrow_id_seq OWNED BY public.borrow.id;


--
-- Name: library; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.library (
    id bigint NOT NULL,
    address character varying(200) NOT NULL,
    email character varying(100),
    name character varying(50) NOT NULL,
    phone character varying(45),
    zip_code character varying(5) NOT NULL,
    city character varying(50)
);


ALTER TABLE public.library OWNER TO admin_db_library;

--
-- Name: library_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.library_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.library_id_seq OWNER TO admin_db_library;

--
-- Name: library_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.library_id_seq OWNED BY public.library.id;


--
-- Name: library_role; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.library_role (
    id bigint NOT NULL,
    library_user_role character varying(255)
);


ALTER TABLE public.library_role OWNER TO admin_db_library;

--
-- Name: library_role_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.library_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.library_role_id_seq OWNER TO admin_db_library;

--
-- Name: library_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.library_role_id_seq OWNED BY public.library_role.id;


--
-- Name: stock; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.stock (
    id bigint NOT NULL,
    available integer,
    outside integer,
    total integer
);


ALTER TABLE public.stock OWNER TO admin_db_library;

--
-- Name: stock_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stock_id_seq OWNER TO admin_db_library;

--
-- Name: stock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.stock_id_seq OWNED BY public.stock.id;


--
-- Name: user_reader; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.user_reader (
    username character varying(55) NOT NULL,
    active boolean NOT NULL,
    address character varying(100) NOT NULL,
    creation_date timestamp without time zone,
    email character varying(100),
    firstname character varying(255) NOT NULL,
    lastname character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    zip_code character varying(5) NOT NULL,
    city character varying(50)
);


ALTER TABLE public.user_reader OWNER TO admin_db_library;

--
-- Name: user_reader_roles; Type: TABLE; Schema: public; Owner: admin_db_library
--

CREATE TABLE public.user_reader_roles (
    library_user_username character varying(255) NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE public.user_reader_roles OWNER TO admin_db_library;

--
-- Name: book id; Type: DEFAULT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);


--
-- Name: borrow id; Type: DEFAULT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.borrow ALTER COLUMN id SET DEFAULT nextval('public.borrow_id_seq'::regclass);


--
-- Name: library id; Type: DEFAULT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.library ALTER COLUMN id SET DEFAULT nextval('public.library_id_seq'::regclass);


--
-- Name: library_role id; Type: DEFAULT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.library_role ALTER COLUMN id SET DEFAULT nextval('public.library_role_id_seq'::regclass);


--
-- Name: stock id; Type: DEFAULT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.stock ALTER COLUMN id SET DEFAULT nextval('public.stock_id_seq'::regclass);


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.book VALUES (2, 'Robert Kiyosaki', 'https://images-na.ssl-images-amazon.com/images/I/91VokXkn8hL.jpg', 'English', 'Rich Dad Poor Dad', 'Rich Dad Poor Dad is Robert''s story of growing up with two dads — his real father and the father of his best friend, his rich dad — and the ways in which both men shaped his thoughts about money and investing.', '2007-04-05 14:53:14', 'Plata Publishing', 'Biography', NULL, 1, 2);
INSERT INTO public.book VALUES (1, 'Dan Brown', 'https://images-na.ssl-images-amazon.com/images/I/81E2GoEbOPL.jpg', 'English', 'Da Vinci Code', 'As millions of readers around the globe have already discovered, The Da Vinci Code is a reading experience unlike any other. Simultaneously lightning-paced, intelligent, and intricately layered with remarkable research and detail.', '2003-03-18 14:49:35', 'Robert Langdon', 'Thrillers', NULL, 1, 1);
INSERT INTO public.book VALUES (3, 'Napoleon Hill', 'https://images-na.ssl-images-amazon.com/images/I/71UypkUjStL.jpg', 'English', 'Think and Grow Rich', 'Think and Grow Rich has been called the "Granddaddy of All Motivational Literature." It was the first book to boldly ask, "What makes a winner?" The man who asked and listened for the answer.', '2005-08-05 14:56:37', 'Tarcher Perigee', 'Story', NULL, 1, 3);
INSERT INTO public.book VALUES (4, 'J.K.Rowling', 'https://m.media-amazon.com/images/I/619tkhTTJzL.jpg', 'English', 'Harry Potter and the Chamber of Secrets', 'Harry Potter''s summer has included the worst birthday ever, doomy warnings from a house-elf called Dobby, and rescue from the Dursleys by his friend Ron Weasley in a magical flying car!', '1998-03-29 22:51:16', 'Pottermore Publishing', 'Story', NULL, 1, 4);
INSERT INTO public.book VALUES (5, 'J.R.R.Tolkien', 'https://images-na.ssl-images-amazon.com/images/I/51MlPWDaXGL._SX331_BO1,204,203,200_.jpg', 'English', 'The Return of the King', 'One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them
', '2012-03-24 22:51:16', 'lord Publishing', 'Story', NULL, 1, 5);
INSERT INTO public.book VALUES (6, 'MJ DeMarco', 'https://m.media-amazon.com/images/I/51MsMgvIUcL.jpg', 'English', 'The Millionaire Fastlane', 'Has the "settle-for-less" financial plan become your plan for wealth? That plan sounds a little something like this:"Graduate from college, get a good job, save 10% of your paycheck, buy a used car, cancel the movie channels', '2012-03-24 22:51:16', 'Viperion corp', 'Biography', NULL, 1, 6);
INSERT INTO public.book VALUES (7, 'Michael T. Nygard', 'https://images-na.ssl-images-amazon.com/images/I/419zAwJJH4L._SX415_BO1,204,203,200_.jpg', 'English', 'Release It!', 'A single dramatic software failure can cost a company millions of dollars - but can be avoided with simple changes to design and architecture.', '2012-03-24 22:51:16', 'Pragmatic Bookshelf', 'IT', NULL, 1, 7);


--
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.booking VALUES (5, 'john33', '2020-03-29', NULL, false, NULL);
INSERT INTO public.booking VALUES (2, 'john33', '2020-03-29', NULL, false, NULL);


--
-- Data for Name: borrow; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.borrow VALUES (5, false, '2020-03-25 14:44:21', '2020-03-10 14:44:18', false, 1, 'test');
INSERT INTO public.borrow VALUES (3, false, '2020-03-01 00:00:00', '2019-12-05 00:00:00', true, 3, 'john33');
INSERT INTO public.borrow VALUES (7, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 2, 'test');
INSERT INTO public.borrow VALUES (8, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 3, 'test');
INSERT INTO public.borrow VALUES (9, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', true, 4, 'test');
INSERT INTO public.borrow VALUES (10, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 5, 'test');
INSERT INTO public.borrow VALUES (11, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 6, 'test');
INSERT INTO public.borrow VALUES (12, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 7, 'test');
INSERT INTO public.borrow VALUES (13, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 3, 'test');
INSERT INTO public.borrow VALUES (14, false, '2020-04-29 22:42:08', '2020-03-29 22:42:12', false, 2, 'test');
INSERT INTO public.borrow VALUES (1, false, '2020-04-28 00:00:00', '2020-03-29 00:00:00', true, 1, 'john33');


--
-- Data for Name: library; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.library VALUES (1, '10 Rue du Général Camou', 'info@americanlibraryinparis.org', 'The American Library in Paris', '01 53 59 12 60', '75007', 'Paris');


--
-- Data for Name: library_role; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.library_role VALUES (1, 'user');
INSERT INTO public.library_role VALUES (2, 'admin');


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.stock VALUES (3, 0, 8, 8);
INSERT INTO public.stock VALUES (2, 0, 6, 6);
INSERT INTO public.stock VALUES (1, 2, 6, 8);
INSERT INTO public.stock VALUES (4, 3, 5, 8);
INSERT INTO public.stock VALUES (5, 0, 7, 7);
INSERT INTO public.stock VALUES (6, 3, 7, 10);
INSERT INTO public.stock VALUES (7, 4, 4, 8);


--
-- Data for Name: user_reader; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.user_reader VALUES ('test', true, 'rue du chat', '2019-12-05 18:06:08', 'dzdevsoft@gmail.com', 'Da Vinci Code', 'Dan Brown', 'English', '66600', 'Paris');
INSERT INTO public.user_reader VALUES ('john33', true, '3 route du chat', '2019-11-05 15:00:15', 'johnSnow@gmail.com', 'Snow', 'John', '12345', '69006', 'Lyon');
INSERT INTO public.user_reader VALUES ('john3', true, '4 route du chat', '2020-03-29 22:56:31', 'johnSnow2@gmail.com', 'Sand', 'John', '', '13000', 'Westeros');
INSERT INTO public.user_reader VALUES ('john44', true, '3 route du chat', '2020-03-30 01:55:59.728', 'johnSnow@gmail.com', 'Snowden', 'Johnny', '12544', '69006', NULL);


--
-- Data for Name: user_reader_roles; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

INSERT INTO public.user_reader_roles VALUES ('john33', 1);


--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.book_id_seq', 7, true);


--
-- Name: borrow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.borrow_id_seq', 14, true);


--
-- Name: library_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.library_id_seq', 1, true);


--
-- Name: library_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.library_role_id_seq', 2, true);


--
-- Name: stock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.stock_id_seq', 7, true);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (bookid, library_userid);


--
-- Name: borrow borrow_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT borrow_pkey PRIMARY KEY (id);


--
-- Name: library library_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.library
    ADD CONSTRAINT library_pkey PRIMARY KEY (id);


--
-- Name: library_role library_role_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.library_role
    ADD CONSTRAINT library_role_pkey PRIMARY KEY (id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: user_reader user_reader_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.user_reader
    ADD CONSTRAINT user_reader_pkey PRIMARY KEY (username);


--
-- Name: user_reader_roles fk264r05hip8e34rq43io7lp05d; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.user_reader_roles
    ADD CONSTRAINT fk264r05hip8e34rq43io7lp05d FOREIGN KEY (roles_id) REFERENCES public.library_role(id);


--
-- Name: borrow fk7wy1me38vjlxi9vfv7p5tb9ta; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fk7wy1me38vjlxi9vfv7p5tb9ta FOREIGN KEY (user_id) REFERENCES public.user_reader(username);


--
-- Name: book fkaojxagnfmppd09k35kye5eph5; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkaojxagnfmppd09k35kye5eph5 FOREIGN KEY (library_id) REFERENCES public.library(id);


--
-- Name: book fkcdlp3bj6butapn6ixbomswr6; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkcdlp3bj6butapn6ixbomswr6 FOREIGN KEY (stock_id) REFERENCES public.stock(id);


--
-- Name: book fkch8cvxf27j7e0jy8ss458ie3g; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkch8cvxf27j7e0jy8ss458ie3g FOREIGN KEY (borrow_id) REFERENCES public.borrow(id);


--
-- Name: borrow fke838cg4fs2k5c42k5whlu2cnv; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fke838cg4fs2k5c42k5whlu2cnv FOREIGN KEY (bookid_id) REFERENCES public.book(id);


--
-- Name: user_reader_roles fkp77wxqwbkm47a4lnbnh1q0km9; Type: FK CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.user_reader_roles
    ADD CONSTRAINT fkp77wxqwbkm47a4lnbnh1q0km9 FOREIGN KEY (library_user_username) REFERENCES public.user_reader(username);


--
-- PostgreSQL database dump complete
--

