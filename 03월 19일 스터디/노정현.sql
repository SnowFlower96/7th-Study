use world;

# 노정현
# 1. city 테이블의 population 열에 인덱스를 추가하고 제거하기
show index from city;

create index population on city(population);
alter table city add index population (name);
show index from city;

drop index population on city;
alter table city drop index population;
show index from city;

# 2. 국가별 인구수가 100만이 넘는 도시의 수를 출력하기
select co.name, count(ci.name) "도시의 수"
from country co 
join 
(select *
from city ci
where ci.population > 1000000) ci
on co.code = ci.countrycode
group by co.name;

# 3. 가장 다양한 언어를 사용하는 국가의 이름과 언어의 수를 출력하기
select co.name, count(cl.language) "언어의 수"
from countrylanguage cl join (
	select code, name
    from country
) co
on cl.countrycode = co.code
group by countrycode;

# 4. 수도의 인구보다 많은 도시가 존재하는 국가들의 이름과 수도의 인구보다 많은 도시의 수를 출력하기
select ca.name, 
ca.population "수도의 인구", count(ci.countrycode) "도시의 수"
from city ci join (
select country.code, country.name, ci.population
from city ci join country
on ci.id = country.capital) ca
on ci.countrycode = ca.code
where ci.population > ca.population
group by ci.countrycode;

select ca.name, ca.population "수도의 인구", 
ci.name "도시", ci.population "도시의 인구"
from city ci join (
select country.code, country.name, ci.population
from city ci join country
on ci.id = country.capital) ca
on ci.countrycode = ca.code
where ci.population > ca.population;

# 5. 대륙별 국가의 숫자와 인구의 합을 출력하기, 단 국가의 수를 기준으로 내림차순 정렬
select continent, count(code) "국가의 수", sum(population) "총 인구수"
from country
group by continent
order by 2 desc;


# 정원석
use ssafydb;
# 1. 'David'라는 first_name을 가진 사원 중 start_date가 가장 빠른 사원을 찾으세요.
select first_name, last_name, start_date
from employees inner join job_history
using (employee_id)
where first_name = "david";

#2. Seattle에서 근무하는 사원의 급여 평균을 구하세요.(소수점 두자리수까지 구하시오)
select round(avg(salary), 2) "Seattle의 급여 평균"
from employees
where department_id in (select department_id
from locations join departments
using (location_id)
where city = "Seattle");

# 3. 'United States of America'에서 근무하는 인원의 수를 구하시오.
select count(employee_id) "인원수"
from employees
where department_id in (
	select department_id
	from departments
	where location_id in (
		select location_id
		from locations join countries
		using (country_id)
		where country_name = "United States of America"
    )
);

# 4. 국가별 급여의 평균을 구하세요. 소수점 두자리수까지 구하시오.(4건)
select country_id, round(avg(salary), 2) "평균 급여"
from employees emp join (
	select country_id, department_id
	from departments join locations
	using (location_id)) dep
using (department_id)
group by country_id;

# 5. (world 사용)기대수명이 70이상인 국가 중 인구수가 가장 적은 도시를 구하시오. (코드 기준으로 오름차순으로 정렬하시오) (111건)
use world;
select code, le.name "나라이름", ci.name "도시", population
from city ci join (
	select code, name
	from country
	where LifeExpectancy >= 70) le
on ci.countrycode = le.code
where (code, population) in (
	select code, min(ci.population)
	from city ci join (
		select code, name
		from country
		where LifeExpectancy >= 70) le
	on ci.countrycode = le.code
    group by code
)
order by code;


# 허유민
use ssafydb;
# 1. employees에서 전화번호가 ‘515’로 시작하면서 고용일의 달(month)가 5부터 9 까지인 직원의 정보를 출력하시오.
select *
from employees
where phone_number like "515%" and month(hire_date) between 5 and 9;

# 2. employees에서 2000년도에 고용된 직원들의 부서별 급여의 합을 출력하시오. ( 부서번호와 급여의 합 출력)
select department_id, sum(salary)
from employees
where year(hire_date) = 2000
group by department_id;

# 3. employees에서 manager_id별 평균 급여가 7000 미만인 manager_id 와 평균 급여 출력, manager 기준으로 내림차순으로 정렬하시오.
select manager_id, avg(salary) as "평균 급여"
from employees
group by manager_id
having `평균 급여` < 7000
order by manager_id desc;

# 4. employees에서 부서별로 고용일이 가장 빠른 날짜를 선택하시오. 부서번호와 고용일 출력하시오.
select department_id, min(hire_date)
from employees
group by department_id;

# 5. employees에서 고용년도별 입사한 직원이 5명 이상인 고용년도와 평균 급여를 출력 하시오.
select year(hire_date), count(employee_id) "입사한 직원 수", avg(salary)
from employees
group by year(hire_date)
having `입사한 직원 수` >= 5;


# 허예강
drop database if exists yegang;
create database yegang; # 한번 실행후 주석

use yegang;

create table product(
	id varchar(30),
    name varchar(30),
    cost varchar(30),
    year varchar(30),
    city varchar(30)  
);

create table sale(
	id varchar(30),
    product_id varchar(30),
    price varchar(30),
    year varchar(30),
    city varchar(30)  
);

insert into product (id, name, cost, year, city) value
('1', 'chair', '245', '2017', 'seoul'),
('2', 'armchair', '500', '2018', 'seoul'),
('3', 'desk', '900', '2019', 'daejeon'),
('4', 'lamp', '85', '2017', 'cheonan'),
('5', 'bench', '2000', '2018', 'busan'),
('6', 'stool', '2500', '2020', 'isekai'),
('7', 'table', '2000', '2020', 'isekai');

insert into sale (id, product_id, price, year, city) value
('1', '2', '2000', '2020', 'seoul'),
('2', '2', '590', '2020', 'newyork'),
('3', '2', '790', '2020', 'cheonan'),
('5', '3', '800', '2019', 'cheonan'),
('6', '4', '100', '2020', 'ssafy'),
('7', '5', '2300', '2019', 'busan'),
('8', '7', '2000', '2020', 'newyork');
# 1. sale에서 2000원에 팔린 product_id와 product 테이블의 id와 일치하는 것을 출력하세요. (2건)
# 1-1. 서브쿼리
select *
from product
where product.id in (
	select product_id
	from sale
	where price = 2000
);
# 1-2. join
select *
from product join sale
on product.id = sale.product_id
where price = 2000;

# 2. 판매된(sale) 상품들의 이름과 가격을 출력하세요. (5건)
# 2-1. 서브쿼리
select name, cost
from product
where id in (select product_id from sale);

# 2-2. join
select name, cost
from product join sale
on product.id = sale.product_id
group by product.id;

# 3. 2020년도에 팔리지 않은 상품들의 정보를 출력하세요. (4건)
# 3-1. 서브쿼리
select *
from product
where id not in (select product_id from sale where year = 2020);

# 3-1. join
select pro.id, pro.name, pro.cost, pro.year, pro.city
from product pro left outer join (
	select *
    from sale
    where year = 2020
) as `2020`
on pro.id = `2020`.product_id
where `2020`.id is null;


# 허혜진
use ssafydb;
# 1. 1997년도에 입사한 사원의 사번(eid), 급여(sal), 입사날짜(h_date)만 emp_1997이라는 이름으로 테이블을 생성하시오. 28건
create view emp_1997 as
select employee_id "eid", salary "sal", hire_date "h_date"
from employees
where year(hire_date) = 1997;

select * from emp_1997;

# 2. 10월에 입사한 사원의 부서위치 출력. 17건
select city
from (select departments.location_id
from employees join departments
using (department_id)
where month(hire_date) = 10) as emp_10 join locations
on emp_10.location_id = locations.location_id;

# 3. 회사에 존재하는 모든 부서의 부서이름과 부서에서 근무하는 사원의 사번, commission_cnt 출력. 122건
select department_name, employee_id, commission_pct
from departments dep left outer join employees emp
using (department_id);

# 4. 근무 도시가 'Toronto'인 사원의 사번, 이름 출력. 2건
select employee_id, concat(first_name, " ", last_name) "이름"
from employees
where department_id in (
	select department_id
	from departments join locations
	using (location_id)
	where city = "Toronto"
);

# 5. 회사에 근무하는 모든 사원의 사번, 이름, 매니저 번호 출력. 107건
select employees.employee_id, concat(first_name, " ", last_name) "이름", emp.phone_number "매니저 번호"
from employees left outer join (select employee_id, phone_number from employees) emp
on employees.manager_id = emp.employee_id;