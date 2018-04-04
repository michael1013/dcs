<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.gnetop.dcs.dao.schema.User">
	<sql id="userColumns">
		userid id,
		userid,
		username,
		serverid,
		nickname,
		mobile,
		lat,
		lng,
		device_token deviceToken,
		token,
		token_date tokenDate,
		create_time createTime,
		last_login_time lastLoginTime,
		update_time updateTime,
		del_flag delFlag,
		is_guest isGuest
	</sql>
	
	<select id="findOne" parameterType="User" resultType="User">
		(SELECT 
		<include refid="userColumns"/>
		FROM t_dcs_user u
		<where>
			<if test="userid != null and userid != ''">AND u.userid = #{userid}</if>
			<if test="username != null and username != ''">AND u.username = #{username}</if>
			<if test="serverid != null and serverid != ''">AND u.serverid = #{serverid}</if>
			<if test="nickname != null and nickname != ''">AND u.nickname = #{nickname}</if>
			<if test="mobile != null and mobile != ''">AND u.mobile = #{mobile}</if>
			<if test="lat != null and lat != ''">AND u.lat = #{lat}</if>
			<if test="lng != null and lng != ''">AND u.lng = #{lng}</if>
			<if test="deviceToken != null and deviceToken != ''">AND u.device_token = #{deviceToken}</if>
			<if test="token != null and token != ''">AND u.token = #{token}</if>
			<if test="tokenDate != null and tokenDate != ''">AND u.token_date = #{tokenDate}</if>
			<if test="createTime != null and createTime != ''">AND u.create_time = #{createTime}</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">AND u.last_login_time = #{lastLoginTime}</if>
			<if test="updateTime != null and updateTime != ''">AND u.update_time = #{updateTime}</if>
			<if test="delFlag != null and delFlag != ''">AND u.del_flag = #{delFlag}</if>
			<if test="isGuest != null and isGuest != ''">AND u.is_guest = #{isGuest}</if>
		</where>
		)LIMIT 1
	</select>
	
	<select id="findAll" resultType="User">
		(
		SELECT
		<include refid="userColumns"></include>
		FROM t_dcs_user u
		<where>
			0 = 0
			<if test="userid != null and userid != ''">AND u.userid = LIKE CONCAT('%',#{userid},'%')</if>
			<if test="username != null and username != ''">OR u.username LIKE CONCAT('%',#{username},'%')</if>
			<if test="serverid != null and serverid != ''">AND u.serverid = #{serverid}</if>
			<if test="nickname != null and nickname != ''">OR u.nickname LIKE CONCAT('%',#{nickname},'%')</if>
			<if test="mobile != null and mobile != ''">OR u.mobile LIKE CONCAT('%',#{mobile},'%')</if>
			<if test="lat != null and lat != ''">AND u.lat = #{lat}</if>
			<if test="lng != null and lng != ''">AND u.lng = #{lng}</if>
			<if test="deviceToken != null and deviceToken != ''">AND u.device_token = #{deviceToken}</if>
			<if test="token != null and token != ''">AND u.token = #{token}</if>
			<if test="tokenDate != null and tokenDate != ''">AND u.token_date = #{tokenDate}</if>
			<if test="createTime != null and createTime != ''">AND u.create_time = #{createTime}</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">AND u.last_login_time = #{lastLoginTime}</if>
			<if test="updateTime != null and updateTime != ''">AND u.update_time = #{updateTime}</if>
			<if test="delFlag != null and delFlag != ''">AND u.del_flag = #{delFlag}</if>
			<if test="isGuest != null and isGuest != ''">AND u.is_guest = #{isGuest}</if>
			
			<if test="begin != null and begin != ''">AND u.create_time &gt; #{begin}</if>
			<if test="end != null and end != ''">AND u.create_time &lt; #{end}</if>
		</where>
		)
	</select>
	
	<select id="findList" resultType="User">
		(
		SELECT
		<include refid="userColumns"></include>
		FROM t_dcs_user u
		<where>
			0 = 0
			<if test="userid != null and userid != ''">AND u.userid = LIKE CONCAT('%',#{userid},'%')</if>
			<if test="username != null and username != ''">OR u.username LIKE CONCAT('%',#{username},'%')</if>
			<if test="serverid != null and serverid != ''">AND u.serverid = #{serverid}</if>
			<if test="nickname != null and nickname != ''">OR u.nickname LIKE CONCAT('%',#{nickname},'%')</if>
			<if test="mobile != null and mobile != ''">OR u.mobile LIKE CONCAT('%',#{mobile},'%')</if>
			<if test="lat != null and lat != ''">AND u.lat = #{lat}</if>
			<if test="lng != null and lng != ''">AND u.lng = #{lng}</if>
			<if test="deviceToken != null and deviceToken != ''">AND u.device_token = #{deviceToken}</if>
			<if test="token != null and token != ''">AND u.token = #{token}</if>
			<if test="tokenDate != null and tokenDate != ''">AND u.token_date = #{tokenDate}</if>
			<if test="createTime != null and createTime != ''">AND u.create_time = #{createTime}</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">AND u.last_login_time = #{lastLoginTime}</if>
			<if test="updateTime != null and updateTime != ''">AND u.update_time = #{updateTime}</if>
			<if test="delFlag != null and delFlag != ''">AND u.del_flag = #{delFlag}</if>
			<if test="isGuest != null and isGuest != ''">AND u.is_guest = #{isGuest}</if>
			
			<if test="begin != null and begin != ''">AND u.create_time &gt; #{begin}</if>
			<if test="end != null and end != ''">AND u.create_time &lt; #{end}</if>
		</where>
		<if test="orderBy != null and orderBy != ''">
			ORDER BY ${orderBy}
		</if>
		)
		<if test="pageSize != null">
			LIMIT #{start}, #{pageSize}
		</if>
	</select>
	
	<select id="countFindList" resultType="Integer">
		(
		SELECT
		count(1) total
		FROM t_dcs_user u
		<where>
			0 = 0
			<if test="userid != null and userid != ''">AND u.userid = LIKE CONCAT('%',#{userid},'%')</if>
			<if test="username != null and username != ''">OR u.username LIKE CONCAT('%',#{username},'%')</if>
			<if test="serverid != null and serverid != ''">AND u.serverid = #{serverid}</if>
			<if test="nickname != null and nickname != ''">OR u.nickname LIKE CONCAT('%',#{nickname},'%')</if>
			<if test="mobile != null and mobile != ''">OR u.mobile LIKE CONCAT('%',#{mobile},'%')</if>
			<if test="lat != null and lat != ''">AND u.lat = #{lat}</if>
			<if test="lng != null and lng != ''">AND u.lng = #{lng}</if>
			<if test="deviceToken != null and deviceToken != ''">AND u.device_token = #{deviceToken}</if>
			<if test="token != null and token != ''">AND u.token = #{token}</if>
			<if test="tokenDate != null and tokenDate != ''">AND u.token_date = #{tokenDate}</if>
			<if test="createTime != null and createTime != ''">AND u.create_time = #{createTime}</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">AND u.last_login_time = #{lastLoginTime}</if>
			<if test="updateTime != null and updateTime != ''">AND u.update_time = #{updateTime}</if>
			<if test="delFlag != null and delFlag != ''">AND u.del_flag = #{delFlag}</if>
			<if test="isGuest != null and isGuest != ''">AND u.is_guest = #{isGuest}</if>
			
			<if test="begin != null and begin != ''">AND u.create_time &gt; #{begin}</if>
			<if test="end != null and end != ''">AND u.create_time &lt; #{end}</if>
		</where>
		)
	</select>
	
	<select id="findNew" parameterType="User" resultType="HashMap">
		SELECT
			date_format(u.create_time, "%Y-%m-%d") date, 
			IFNULL(COUNT(1), 0) count
		FROM
			t_dcs_user u
		WHERE 
			u.create_time BETWEEN #{begin} AND #{end}
		GROUP BY 
			date_format(u.create_time, "%Y-%m-%d")
	</select>
	
	<select id="findNewUserCount" parameterType="HashMap" resultType="UserData">
		SELECT
		i.increase
		FROM
		(SELECT COUNT(1) increase FROM t_dcs_user u
		WHERE u.create_time between #{begin} and #{end}) i
	</select>
	
	<insert id="insert" parameterType="User" useGeneratedKeys="true"
		keyProperty="userid">
		insert into t_dcs_user
		<trim prefix="(" suffixOverrides="," suffix=")">
			<if test="username != null and username != ''">
				username,
			</if>
			<if test="serverid != null and serverid != ''">
				serverid,
			</if>
			<if test="nickname != null and nickname != ''">
				nickname,
			</if>
			<if test="mobile != null and mobile != ''">
				mobile,
			</if>
			<if test="lat != null">
				lat,
			</if>
			<if test="lng != null">
				lng,
			</if>
			<if test="deviceToken != null and deviceToken != ''">
				device_token,
			</if>
			<if test="token != null and token != ''">
				token,
			</if>
			<if test="tokenDate != null and tokenDate != ''">
				token_date,
			</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">
				last_login_time,
			</if>
			<if test="createTime != null and createTime != ''">
				create_time,
			</if>
			<if test="updateTime != null and updateTime != ''">
				update_time,
			</if>
			<if test="delFlag != null">
				del_flag,
			</if>
			<if test="isGuest != null">
				is_guest
			</if>
		</trim>
		VALUES
		<trim prefix="(" suffixOverrides="," suffix=")">
			<if test="username != null and username != ''">
				#{username},
			</if>
			<if test="serverid != null and serverid != ''">
				#{serverid},
			</if>
			<if test="nickname != null and nickname != ''">
				#{nickname},
			</if>
			<if test="mobile != null and mobile != ''">
				#{mobile},
			</if>
			<if test="lat != null">
				#{lat},
			</if>
			<if test="lng != null">
				#{lng},
			</if>
			<if test="deviceToken != null and deviceToken != ''">
				#{deviceToken},
			</if>
			<if test="token != null and token != ''">
				#{token},
			</if>
			<if test="tokenDate != null and tokenDate != ''">
				#{tokenDate},
			</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">
				#{lastLoginTime},
			</if>
			<if test="createTime != null and createTime != ''">
				#{createTime},
			</if>
			<if test="updateTime != null and updateTime != ''">
				#{updateTime},
			</if>
			<if test="delFlag != null">
				#{delFlag},
			</if>
			<if test="isGuest != null">
				#{isGuest}
			</if>
		</trim>
	</insert>
	
	<update id="update" parameterType="User">
		UPDATE t_dcs_user
		<set>
			<if test="nickname != null and nickname != ''">
				nickname = #{nickname},
			</if>
			<if test="serverid != null and serverid != ''">
				serverid = #{serverid},
			</if>
			<if test="mobile != null and mobile != ''">
				mobile = #{mobile},
			</if>
			<if test="lat != null">
				lat = #{lat},
			</if>
			<if test="lng != null">
				lng = #{lng},
			</if>
			<if test="deviceToken != null and deviceToken != ''">
				deviceToken = #{deviceToken},
			</if>
			<if test="token != null and token != ''">
				token = #{token},
			</if>
			<if test="tokenDate != null and tokenDate != ''">
				token_date = #{tokenDate},
			</if>
			<if test="lastLoginTime != null and lastLoginTime != ''">
				last_login_time = #{lastLoginTime},
			</if>
			<if test="createTime != null and createTime != ''">
				create_time = #{createTime},
			</if>
			<if test="updateTime != null and updateTime != ''">
				update_time = #{updateTime},
			</if>
			<if test="delFlag != null">
				del_flag = #{delFlag},
			</if>
			<if test="isGuest != null">
				is_guest = #{isGuest}
			</if>
		</set>
		WHERE
			userid = #{userid}
	</update>
</mapper>