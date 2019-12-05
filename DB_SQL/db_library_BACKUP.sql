--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

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


ALTER TABLE public.book OWNER TO admin_db_library;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_db_library
--

CREATE SEQUENCE public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_id_seq OWNER TO admin_db_library;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_db_library
--

ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;


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

COPY public.book (id, author, cover_pic_url, language, name, overview, publication_date, publisher, subject, borrow_id, library_id, stock_id) FROM stdin;
2	Robert Kiyosaki	https://images-na.ssl-images-amazon.com/images/I/91VokXkn8hL.jpg	English	Rich Dad Poor Dad	Rich Dad Poor Dad is Robert's story of growing up with two dads — his real father and the father of his best friend, his rich dad — and the ways in which both men shaped his thoughts about money and investing.	2007-04-05 14:53:14	Plata Publishing	Biography	\N	1	2
1	Dan Brown	https://images-na.ssl-images-amazon.com/images/I/81E2GoEbOPL.jpg	English	Da Vinci Code	As millions of readers around the globe have already discovered, The Da Vinci Code is a reading experience unlike any other. Simultaneously lightning-paced, intelligent, and intricately layered with remarkable research and detail.	2003-03-18 14:49:35	Robert Langdon	Thrillers	\N	1	1
3	Napoleon Hill	https://images-na.ssl-images-amazon.com/images/I/71UypkUjStL.jpg	English	Think and Grow Rich	Think and Grow Rich has been called the "Granddaddy of All Motivational Literature." It was the first book to boldly ask, "What makes a winner?" The man who asked and listened for the answer.	2005-08-05 14:56:37	Tarcher Perigee	Story	\N	1	3
\.


--
-- Data for Name: borrow; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.borrow (id, closed, date_end, date_start, extra_time, bookid_id, user_id) FROM stdin;
3	f	2020-03-04 00:00:00	2019-12-05 00:00:00	f	3	john33
2	f	2020-02-02 00:00:00	2019-12-05 00:00:00	f	2	john33
1	f	2020-06-13 00:00:00	2019-11-05 00:00:00	f	1	john33
\.


--
-- Data for Name: library; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.library (id, address, email, name, phone, zip_code, city) FROM stdin;
1	10 Rue du Général Camou	info@americanlibraryinparis.org	The American Library in Paris	01 53 59 12 60	75007	Paris
\.


--
-- Data for Name: library_role; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.library_role (id, library_user_role) FROM stdin;
1	user
2	admin
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.stock (id, available, outside, total) FROM stdin;
1	5	6	11
2	6	6	12
3	3	20	23
\.


--
-- Data for Name: user_reader; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.user_reader (username, active, address, creation_date, email, firstname, lastname, password, zip_code, city) FROM stdin;
john33	t	3 route du chat	2019-11-05 15:00:15	johnSnow@gmail.com	Snow	John	12345	69006	Lyon
coco2	t	rue du chat	2019-12-05 18:06:08	d@hotmail.fr	Da Vinci Code	Dan Brown	English	66600	Paris
\.


--
-- Data for Name: user_reader_roles; Type: TABLE DATA; Schema: public; Owner: admin_db_library
--

COPY public.user_reader_roles (library_user_username, roles_id) FROM stdin;
john33	1
\.


--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.book_id_seq', 3, true);


--
-- Name: borrow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_db_library
--

SELECT pg_catalog.setval('public.borrow_id_seq', 3, true);


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

SELECT pg_catalog.setval('public.stock_id_seq', 3, true);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_db_library
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


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

