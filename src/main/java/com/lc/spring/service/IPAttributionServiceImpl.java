package com.lc.spring.service;

import com.lc.crm.util.IPUtil;
import com.lc.spring.entity.IPAttribution;
import com.lc.spring.repository.IPAttributionRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPAttributionServiceImpl implements IPAttributionService {

	public static Log log = LogFactory.getLog(IPAttributionServiceImpl.class);

	@Autowired
	private IPAttributionRepository iPAttributionRepository;

	public IPAttributionServiceImpl(IPAttributionRepository iPAttributionRepository) {
		this.iPAttributionRepository = iPAttributionRepository;
	}

	public IPAttributionServiceImpl() {
	}

	@Override
	public String getIPAttribution(String ip) {
		Long ipNumber = IPUtil.ip2long(ip);
		IPAttribution ipAttribution = iPAttributionRepository.getIPAttribution(ipNumber);
		StringBuffer builder = new StringBuffer();
		if (ipAttribution != null) {
			builder.append((ipAttribution.getCountry().equals("") || ipAttribution.getCountry().equals("-")) ? ""
					: ipAttribution.getCountry())
					.append((ipAttribution.getProvince().equals("") || ipAttribution.getProvince().equals("-")) ? ""
							: ipAttribution.getProvince())
					.append((ipAttribution.getCity().equals("") || ipAttribution.getCity().equals("-")) ? ""
							: ipAttribution.getCity());
		}
		return builder.toString();
	}

	private List<IPAttribution> list = null;

	public String getIPAttributionFromMemory(String ip) {

		if (null == list) {
			list = iPAttributionRepository.findAll(new Sort(Sort.Direction.ASC, "smallNumber"));

		}
		IPAttribution ipAttribution = binarySearch(IPUtil.ip2long(ip), 0, list.size());

		StringBuilder builder = new StringBuilder();
		if (null == ipAttribution) {
			return null;
		}

		if (ipAttribution != null) {
			builder.append((ipAttribution.getCountry().equals("") || ipAttribution.getCountry().equals("-")) ? ""
					: ipAttribution.getCountry())
					.append((ipAttribution.getProvince().equals("") || ipAttribution.getProvince().equals("-")) ? ""
							: ipAttribution.getProvince())
					.append((ipAttribution.getCity().equals("") || ipAttribution.getCity().equals("-")) ? ""
							: ipAttribution.getCity());
		}
		return builder.toString();


	}

	@Override
	public String getIpAttributionCountry(String ip) {
		if (null == list) {
			list = iPAttributionRepository.findAll(new Sort(Sort.Direction.ASC, "smallNumber"));

		}
		IPAttribution ipAttribution = binarySearch(IPUtil.ip2long(ip), 0, list.size());
		if (null == ipAttribution) {

			log.info("IP:+" + ip + "所属国家为空");
			return null;
		} else {
			log.info("IP:+" + ip + "所属国家为:   " + ipAttribution.getCountry());
			return ipAttribution.getCountry();
		}


	}

	@Override
	public String getIpAttributionProvince(String ip) {
		if (null == list) {
			list = iPAttributionRepository.findAll(new Sort(Sort.Direction.ASC, "smallNumber"));

		}
		IPAttribution ipAttribution = binarySearch(IPUtil.ip2long(ip), 0, list.size());
		if (null == ipAttribution) {
			log.info("IP:+" + ip + "所属省份为空");
			return null;
		} else {
			log.info("IP:+" + ip + "所属省份为" + ipAttribution.getProvince());
			return ipAttribution.getProvince();
		}
	}


	private IPAttribution binarySearch(Long ipNumber, int low, int high) {

		if (low >= high) {
			return null;
		}

		int mid = (low + high) / 2;

		IPAttribution attribution = list.get(mid);
		if (attribution.getSmallNumber() > ipNumber) {
			return binarySearch(ipNumber, low, mid);
		} else if (attribution.getBigNumber() < ipNumber) {
			return binarySearch(ipNumber, mid + 1, high);
		} else if (attribution.getSmallNumber() <= ipNumber && attribution.getBigNumber() >= ipNumber) {
			return attribution;
		}

		return null;

	}

}
