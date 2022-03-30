use ssafydb;
#허유민
#1. employees에서 전화번호가 ‘515’로 시작하면서 고용일의 달(month)가 5부터 9 까지인 직원의 정보를 출력하시오.
select *
from employees
where phone_number like '515%'
AND month(hire_date) between 5 and 9;

select * from employees;

#2. employees에서 2000년도에 고용된 직원들의 부서별 급여의 합을 출력하시오. ( 부서번호와 급여의 합 출력)
select department_id, sum(salary)
from employees
where year(hire_date) = 2000
group by department_id;

#3. employees에서 manager_id별 평균 급여가 7000 미만인 manager_id 와 평균 급여 출력, manager_id 기준으로 내림차순으로 정렬하시오.
select manager_id, avg(salary) 
from employees
group by manager_id
having avg(salary) < 7000
order by manager_id desc;

#4. employees에서 부서별로 고용일이 가장 빠른 날짜를 선택하시오.
#    부서번호와 고용일 출력하시오.
select department_id, hire_date
from employees
group by department_id
having min(year(hire_date));

#5. employees에서 고용년도별 입사한 직원이 5명 이상인 고용년도와 그 직원들의 평균 급여를 출력 하시오.
select year(hire_date) , avg(salary)
from employees
group by year(hire_date)
having count(*) >= 5;

# 정원석
# 1.'David'라는 first_name을 가진 사원 중 start_date가 가장 빠른 사원을 찾으세요.
select e.first_name, e.last_name, min(jh.start_date)
from employees e, (select employee_id, start_date from job_history) jh
where e.first_name like 'David'
AND e.employee_id = jh.employee_id;

select e.first_name, e.last_name, min(jh.start_date)
from employees e,  job_history jh
group by e.first_name
having first_name like 'David';
# 2. Seattle에서 근무하는 사원의 급여 평균을 구하세요.(소수점 두자리수까지 구하시오)
select round(avg(salary),2)
from employees
where department_id in (
select department_id from departments where location_id = (select location_id from locations where city like 'Seattle'));
select location_id, city from locations where city like 'Seattle';
# 3. 'United States of America'에서 근무하는 인원의 수를 구하시오.
select country_id from countries where country_name like 'United States of America';
select location_id from locations where country_id = ( select country_id from countries where country_name like 'United States of America');
select department_id from departments where location_id in ( select location_id from locations where country_id = ( select country_id from countries where country_name like 'United States of America'));
select count(*) as '인원수' from employees
where department_id in ( select department_id from departments where location_id in ( select location_id from locations where country_id = ( select country_id from countries where country_name like 'United States of America')));
# 4. 국가별 급여의 평균을 구하세요. 소수점 두자리수까지 구하시오.(4건)
select round(avg(salary),2)
from employees e
join ( select;

# 5. (world 사용)기대수명이 70이상인 국가 중 인구수가 가장 적은 도시를 구하시오. (코드 기준으로 오름차순으로 정렬하시오) (111건)
use world;
select ci.*
from city ci
join country c
on ci.countrycode = c.code
where c.LifeExpectancy >= 70
order by ci.CountryCode;

#order by ci.population desc limit 1;

# 허혜진
use ssafydb;
# 1. 1997년도에 입사한 사원의 사번(eid), 급여(sal), 입사날짜(h_date)만 emp_1997이라는 이름으로 테이블을 생성하시오. 28건
create table emp_1997
select employee_id eid, salary sal, hire_date h_data
from employees
where YEAR(hire_date) = 1997;
# 2. 10월에 입사한 사원의 부서위치 출력. 17건
select department_id ,location_id
from departments
where department_id in
(select department_id
from employees
where month(hire_date) = 10);
# 3. 회사에 존재하는 모든 부서의 부서이름과 부서에서 근무하는 사원의 사번, commission_cnt 출력.  122건
select d.department_id, d.department_name, e.employee_id, e.commission_pct
from employees e
right join departments d
using (department_id);
# 4. 근무 도시가 'Toronto'인 사원의 사번, 이름 출력. 2건
select employee_id, first_name
from employees
where department_id in (
select department_id from departments where location_id in (select location_id from locations where city like 'Toronto'));
# 5. 회사에 근무하는 모든 사원의 사번, 이름, 매니저 번호 출력. 107건
select e.employee_id, e.first_name, d.manager_id
from employees e
left outer join departments d
using(department_id);

# 노정현
use world;
#1. world DB - city 테이블의 population 열에 인덱스를 추가하고 제거하기
alter table city add index test (population);
SHOW INDEX FROM city;
alter table city drop index test;
#2. world DB - 국가별 인구수가 100만이 넘는 도시의 수를 출력하기
select countrycode,name, count(name)
from city
group by countrycode 
having countrycode in ( select code from country where population > 1000000);

#3. world DB - 가장 다양한 언어를 사용하는 국가의 이름과 언어의 수를 출력하기
select c.name, cl.언어의수
from country c, (select countrycode, count(*) as '언어의수' from countrylanguage group by countrycode) cl
where c.code = cl.countrycode
order by cl.언어의수 desc;

#4. world DB - 수도의 인구보다 많은 도시가 존재하는 국가들의 이름과 수도의 인구보다 많은 도시의 수를 출력하기
select *
from city ci join (
select c.code, c.capital, ci.population
from country c
join city ci
on c.capital = ci.id ) ca
on ci.countrycode = ca.code
where  ci.population > ca.Population
group by ci.countrycode;

#5. world DB - 대륙별 국가의 숫자와 인구의 합을 출력하기, 단 국가의 수를 기준으로 내림차순 정렬
select continent, count(*) as "국가의 숫자", sum(population) as "총 인구수"
from country
group by continent
order by count(*) desc;

use world;
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

#1. sale에서 2000원에 팔린 product_id와 product 테이블의 id와 일치하는 것을 출력하세요. (2건)

# 1-1. 서브쿼리
select *
from product
where id in (select product_id from sale where price=2000);
# 1-2. join
select *
from product p
join sale s
on p.id = s.product_id
where s.price=2000;

-- ------------------------------------------------------------------------

#2. 판매된(sale) 상품들의 이름과 가격을 출력하세요. (5건)

# 2-1. 서브쿼리
select distinct name, cost
from product
where id in (select product_id from sale);
# 2-2. join
select distinct p.name, p.cost
from product p
join sale s
on p.id = s.product_id;
-- ------------------------------------------------------------------------

#3. 2020년도에 팔리지 않은 상품들의 정보를 출력하세요. (4건)

# 3-1. 서브쿼리
select *
from product
where id not in ( select product_id from sale where year = 2020);
# join
select *
from product p
left outer join sale s
on p.id = s.product_id
where s.year != 2020 OR s.year IS NULL;
-- ------------------------------------------------------------------------